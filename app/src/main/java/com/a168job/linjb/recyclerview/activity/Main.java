package com.a168job.linjb.recyclerview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.a168job.linjb.recyclerview.AppContext;
import com.a168job.linjb.recyclerview.R;
import com.a168job.linjb.recyclerview.adapter.MenuAdapter;
import com.a168job.linjb.recyclerview.adapter.MyAdapter;
import com.a168job.linjb.recyclerview.bean.JobFavorite;
import com.a168job.linjb.recyclerview.help.UIHelp;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;

import java.util.ArrayList;

/**
 * Created by linjb on 2016/8/10.
 */

public class Main extends Activity {
    private LRecyclerView mRecycler;
    private ProgressBar mProgressBar;
    private ArrayList<JobFavorite> dataList= new ArrayList<>();
    private MyAdapter mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private MenuAdapter mMenuAdapter;

    private AppContext ac;

    private boolean isRefresh = false;

    private static final int RQUEST_COUNT = 10;
    private static int mCurrent = 0;

    private Handler loadingHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                mProgressBar.setVisibility(View.GONE);
                ArrayList<JobFavorite> list = (ArrayList<JobFavorite>) msg.obj;
                if (isRefresh) {
                    mAdapter.clear();
                    isRefresh = false;
                    mRecycler.refreshComplete();
                    dataList = list;
                    mAdapter.setData(dataList); //将数据全部加载到适配器中
                } else {
//                    if (dataList != null) {
//                        dataList.addAll(list);
//                    } else {
//                        dataList = list;
//                    }
                    if (list.size() < RQUEST_COUNT) {
                        RecyclerViewStateUtils.setFooterViewState(Main.this, mRecycler, RQUEST_COUNT, LoadingFooter.State.TheEnd, null);
                    } else {
                        RecyclerViewStateUtils.setFooterViewState(Main.this, mRecycler, RQUEST_COUNT, LoadingFooter.State.Loading, null);
                    }
                    mAdapter.addAll(list);
                }


            }
        }
    };

    private void addItems(ArrayList<JobFavorite> dataList) {
        mAdapter.addAll(dataList);
        mCurrent += dataList.size();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ac = (AppContext) getApplication();
        loadData();
        initView();
        initEvent();

    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int pageNo = 0;
                if (isRefresh) {
                    pageNo = 0;
                } else if (null != dataList) {
                    pageNo = dataList.size() / RQUEST_COUNT
                            + 1;
                }
                ArrayList<JobFavorite> list = new ArrayList<JobFavorite>();



                list = ac.getFavorites(pageNo);
                if (list.size() > 0) {
                    Message msg = loadingHandler.obtainMessage();
                    msg.what = 1;
                    msg.obj = list;
                    loadingHandler.sendMessage(msg);
                }
            }
        }).start();
    }

    private void initEvent() {
        if (dataList.size() >= 0) {
            mAdapter = new MyAdapter(dataList, this);
//            mMenuAdapter = new MenuAdapter();
//            mMenuAdapter.setDataList(dataList);

            mLRecyclerViewAdapter = new LRecyclerViewAdapter(this, mAdapter);
            mRecycler.setAdapter(mLRecyclerViewAdapter);
            mRecycler.setLayoutManager(new LinearLayoutManager(this));
//            LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecycler);
            mRecycler.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
            mRecycler.setLScrollListener(new LRecyclerView.LScrollListener() {
                @Override
                public void onRefresh() {
                    isRefresh = true;
                    loadData();
                }

                @Override
                public void onScrollUp() {

                }

                @Override
                public void onScrollDown() {

                }

                @Override
                public void onBottom() {
                    LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecycler);
                    loadData();
                }

                @Override
                public void onScrolled(int i, int i1) {

                }
            });

            mAdapter.setmOnItemClickListenr(new MyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    UIHelp.toast(Main.this, position + " onClick");
//                    loadData();
                }

                @Override
                public void onItemLongClick(View view, int position) {
                    UIHelp.toast(Main.this, position + " LongClick");
                }
            });
//            mRecycler.setRefreshing(true);
        }
    }

    private void initView() {
        mRecycler = (LRecyclerView) findViewById(R.id.main_recycler);
        mProgressBar = (ProgressBar) findViewById(R.id.main_progress);
    }
}
