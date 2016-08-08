package com.a168job.linjb.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jdsjlzx.ItemDecoration.StickyHeaderAdapter;

import java.util.ArrayList;

/**
 * Created by linjb on 2016/8/5.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> implements
        StickyHeaderAdapter<MyAdapter.MyHeadHolder>{

    private ArrayList<String> dataList;
    private Context context;

    public MyAdapter(ArrayList<String> dataList , Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.tv.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public long getHeaderId(int i) {
        return  (long) i / 7;
    }

    @Override
    public MyHeadHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.head, viewGroup, false);
        return new MyHeadHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(MyHeadHolder myHeadHolder, int i) {
        myHeadHolder.tv_head.setText(i);
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }

    class MyHeadHolder extends RecyclerView.ViewHolder {
        TextView tv_head;
        public MyHeadHolder(View itemView) {
            super(itemView);
            tv_head = (TextView) itemView.findViewById(R.id.head_tv);
        }
    }


    public void addAll(ArrayList<String> list) {
        int lastIndex = dataList.size();
        if (dataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex,list.size());
        }
    }

    public void clear() {
        dataList.clear();
        notifyDataSetChanged();
    }

}
