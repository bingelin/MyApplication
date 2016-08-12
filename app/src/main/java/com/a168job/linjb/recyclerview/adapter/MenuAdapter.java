package com.a168job.linjb.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a168job.linjb.recyclerview.R;
import com.a168job.linjb.recyclerview.bean.JobFavorite;
import com.github.jdsjlzx.swipe.SwipeMenuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linjb on 2016/8/12.
 */

public class MenuAdapter extends SwipeMenuAdapter<MenuAdapter.DefaultViewHolder> {

    protected ArrayList<JobFavorite> mDataList = new ArrayList<>();

    public MenuAdapter() {
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public List<JobFavorite> getDataList() {
        return mDataList;
    }

    public void setDataList(ArrayList<JobFavorite> list) {
        this.mDataList.clear();
        this.mDataList.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<JobFavorite> list) {
        int lastIndex = this.mDataList.size();
        if (this.mDataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    public void remove(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
        if(position != mDataList.size()){ // 如果移除的是最后一个，忽略
            notifyItemRangeChanged(position, mDataList.size() - position);
        }

    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return view;
    }

    @Override
    public MenuAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(MenuAdapter.DefaultViewHolder holder, int position) {
        DefaultViewHolder viewHolder = holder;
        viewHolder.tv_station.setText(mDataList.get(position).getStation());
        viewHolder.tv_expDate.setText(mDataList.get(position).getExpDate());
        viewHolder.tv_name.setText(mDataList.get(position).getName());
        viewHolder.tv_regDate.setText(mDataList.get(position).getRegDate());
        viewHolder.tv_stowdate.setText(mDataList.get(position).getStowdate());
        viewHolder.tv_site.setText(mDataList.get(position).getUnitNo());
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder {
        TextView tv_station;
        TextView tv_site;
        TextView tv_name;
        TextView tv_stowdate;
        TextView tv_regDate;
        TextView tv_expDate;
        public DefaultViewHolder(View itemView) {
            super(itemView);
            tv_station = (TextView) itemView.findViewById(R.id.item_station);
            tv_site = (TextView) itemView.findViewById(R.id.item_site);
            tv_name = (TextView) itemView.findViewById(R.id.item_name);
            tv_stowdate = (TextView) itemView.findViewById(R.id.item_stowdate);
            tv_regDate = (TextView) itemView.findViewById(R.id.item_regDate);
            tv_expDate = (TextView) itemView.findViewById(R.id.item_expDate);
        }
    }
}