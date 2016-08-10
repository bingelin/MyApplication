package com.a168job.linjb.recyclerview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.a168job.linjb.recyclerview.AppContext;
import com.a168job.linjb.recyclerview.R;
import com.a168job.linjb.recyclerview.Utils.DebugUtils;
import com.a168job.linjb.recyclerview.adapter.MyAdapter;
import com.a168job.linjb.recyclerview.help.UIHelp;

import java.util.ArrayList;

/**
 * Created by linjb on 2016/8/10.
 */

public class Main extends Activity {
    private RecyclerView mRecycler;
    private ArrayList<String> dataList;
    private MyAdapter mAdapter;
    private AppContext ac;

    private static final int TOTAL_COUNT = 34;
    private static final int QUEST_COUNT = 10;
    private static int mCurrent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ac = (AppContext) getApplication();
        initView();
        initEvent();
        loadData();
    }

    private void loadData() {

    }

    private void initEvent() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (mCurrent + dataList.size() > TOTAL_COUNT) {
                break;
            }
            dataList.add("item" + i);
        }
        mAdapter = new MyAdapter(dataList, this);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mAdapter);
        mAdapter.setmOnItemClickListenr(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                UIHelp.toast(Main.this,position+" onClick");
                System.out.println("success----->"+ DebugUtils.formatJson(ac.getFavorites(0),"\n"));
            }

            @Override
            public void onItemLongClick(View view, int position) {
                UIHelp.toast(Main.this, position+" LongClick");
            }
        });
    }

    private void initView() {
        mRecycler = (RecyclerView) findViewById(R.id.main_recycler);

    }
}
