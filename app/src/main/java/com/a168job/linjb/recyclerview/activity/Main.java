package com.a168job.linjb.recyclerview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.a168job.linjb.recyclerview.AppContext;
import com.a168job.linjb.recyclerview.R;
import com.a168job.linjb.recyclerview.adapter.MenuAdapter;
import com.a168job.linjb.recyclerview.adapter.MyAdapter;
import com.a168job.linjb.recyclerview.bean.JobFavorite;
import com.a168job.linjb.recyclerview.help.UIHelp;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

import java.util.ArrayList;

/**
 * Created by linjb on 2016/8/10.
 */

public class Main extends Activity {
    private LRecyclerView mRecycler;
    private ArrayList<JobFavorite> dataList= new ArrayList<>();
    private MyAdapter mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private MenuAdapter mMenuAdapter;

    private AppContext ac;


    private static final int TOTAL_COUNT = 34;
    private static final int QUEST_COUNT = 10;
    private static int mCurrent = 0;

    private Handler loadingHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
//                loadData();
                mAdapter.setData(dataList);
                mMenuAdapter.setDataList(dataList);
            }
        }
    };

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
                dataList = ac.getFavorites(0);
                for (int i = 0; i < dataList.size(); i++) {
                    System.out.println(dataList.get(i).getName()+"\n");
                    if (dataList.size() >= 0) {
                        Message msg = loadingHandler.obtainMessage();
                        msg.what = 1;
                        loadingHandler.sendMessage(msg);
                    }
                }

            }
        }).start();

    }

    private void initEvent() {
        if (dataList.size() >= 0) {
            mAdapter = new MyAdapter(dataList, this);
            mMenuAdapter = new MenuAdapter();
            mMenuAdapter.setDataList(dataList);

            mLRecyclerViewAdapter = new LRecyclerViewAdapter(this, mMenuAdapter);
            mRecycler.setAdapter(mLRecyclerViewAdapter);
            mRecycler.setLayoutManager(new LinearLayoutManager(this));

            mRecycler.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);


//            mRecycler.setSwipeMenuCreator();

//            RecyclerViewUtils.setHeaderView(mRecycler, new SampleHeader(this));
//            RecyclerViewUtils.setFooterView(mRecycler,new S);

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
        }
    }

    private void initView() {
        mRecycler = (LRecyclerView) findViewById(R.id.main_recycler);

    }
}
