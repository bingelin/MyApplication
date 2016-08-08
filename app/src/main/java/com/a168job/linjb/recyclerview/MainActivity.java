package com.a168job.linjb.recyclerview;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnItemClickLitener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.util.RecyclerViewUtils;
import com.github.jdsjlzx.view.LoadingFooter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LRecyclerView mLRecyclerView;
    private ArrayList<String> dataList;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private MyAdapter adapter;
    private String TAG = "Binge";
    private MyHandler mHandler = new MyHandler();

    private static final int TOTAL_COUNTER = 33;
    private static final int REQUEST_COUNTER = 10;
    private static int mCurrentCounter = 0;
    private boolean isRefresh = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLRecyclerView = (LRecyclerView) findViewById(R.id.main_recyclerView);

        dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            dataList.add("item" + i);
        }
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList);
        adapter = new MyAdapter(dataList, this);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(this, adapter);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //设置下拉和上拉刷新的样式
//        mLRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
//        mLRecyclerView.setArrowImageView(R.mipmap.ic_launcher);

        mLRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh() {
                isRefresh = true;
                requestData();
            }

            @Override
            public void onScrollUp() {

            }

            @Override
            public void onScrollDown() {
            }

            @Override
            public void onBottom() {
                LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mLRecyclerView);
                if (state == LoadingFooter.State.Loading) {
                    Log.d(TAG, "the state is loading");
                    return;
                }
                if (mCurrentCounter < TOTAL_COUNTER) {
                    RecyclerViewStateUtils.setFooterViewState(MainActivity.this, mLRecyclerView, REQUEST_COUNTER, LoadingFooter.State.Loading, null);

                    requestData();
                } else {
                    RecyclerViewStateUtils.setFooterViewState(MainActivity.this,mLRecyclerView,REQUEST_COUNTER,LoadingFooter.State.TheEnd,null);
                }
            }

            @Override
            public void onScrolled(int i, int i1) {

            }
        });
        mLRecyclerView.setRefreshing(true);

        //为ListView设置Head
        RecyclerViewUtils.setHeaderView(mLRecyclerView,new SampleHeader(this));

        mLRecyclerViewAdapter.setOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int i) {
                toast(i+" was clicked");
            }

            @Override
            public void onItemLongClick(View view, int i) {
                toast(i+" was long clicked");
            }
        });

    }

    private void requestData() {
        Log.d(TAG, "requestData");
        new Thread() {

            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //模拟一下网络请求失败的情况
                mHandler.sendEmptyMessage(1);
            }
        }.start();
    }

    public void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    class MyHandler extends android.os.Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (isRefresh) {
                        adapter.clear();
                        mCurrentCounter = 0;
                    }

                    int currentSize = adapter.getItemCount();

                    ArrayList<String> newList = new ArrayList<>();
                    for (int i = 0; i < 10 ; i++) {
                        if (newList.size() + currentSize >= TOTAL_COUNTER) {
                            break;
                        }
                        newList.add("item" + (currentSize+i));
                    }

                    addItems(newList);
                    if (isRefresh) {
                        isRefresh = false;
                        //取消顶部刷新界面
                        mLRecyclerView.refreshComplete();
                        notifyDataSetChanged();
                    } else {
                        //设置底部刷新界面的状态：Normal err TheEnd
                        RecyclerViewStateUtils.setFooterViewState(mLRecyclerView, LoadingFooter.State.Normal);
                    }
                    break;
                case 2:
                    if (isRefresh) {
                        isRefresh = false;
                        mLRecyclerView.refreshComplete();
                        RecyclerViewStateUtils.setFooterViewState(MainActivity.this,mLRecyclerView,REQUEST_COUNTER,LoadingFooter.State.NetWorkError,mFootClick);
                    }
                    break;
            }
        }
    }

    private void notifyDataSetChanged() {
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    private void addItems(ArrayList<String> newList) {
        adapter.addAll(newList);
        mCurrentCounter += newList.size();
    }

    private View.OnClickListener mFootClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerViewStateUtils.setFooterViewState(MainActivity.this, mLRecyclerView, REQUEST_COUNTER, LoadingFooter.State.Loading, null);
            requestData();
        }
    };

    /**
     * 创建右滑item
     */
}
