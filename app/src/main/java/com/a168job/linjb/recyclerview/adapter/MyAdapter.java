package com.a168job.linjb.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a168job.linjb.recyclerview.R;
import com.a168job.linjb.recyclerview.bean.JobFavorite;

import java.util.ArrayList;

/**
 * Created by linjb on 2016/8/8.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>  {

    private ArrayList<JobFavorite> dataList;
    private Context context;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListenr(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public MyAdapter(ArrayList<JobFavorite> dataList , Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.tv_station.setText(dataList.get(position).getStation());
        holder.tv_expDate.setText(dataList.get(position).getExpDate());
        holder.tv_name.setText(dataList.get(position).getName());
        holder.tv_regDate.setText(dataList.get(position).getRegDate());
        holder.tv_stowdate.setText(dataList.get(position).getStowdate());
        holder.tv_site.setText(dataList.get(position).getUnitNo());

        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(view,pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(view, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }



    class MyHolder extends RecyclerView.ViewHolder{
        TextView tv_station;
        TextView tv_site;
        TextView tv_name;
        TextView tv_stowdate;
        TextView tv_regDate;
        TextView tv_expDate;

        public MyHolder(View itemView) {
            super(itemView);
            tv_station = (TextView) itemView.findViewById(R.id.item_station);
            tv_site = (TextView) itemView.findViewById(R.id.item_site);
            tv_name = (TextView) itemView.findViewById(R.id.item_name);
            tv_stowdate = (TextView) itemView.findViewById(R.id.item_stowdate);
            tv_regDate = (TextView) itemView.findViewById(R.id.item_regDate);
            tv_expDate = (TextView) itemView.findViewById(R.id.item_expDate);

        }
    }



    public void addAll(ArrayList<JobFavorite> list) {
        int lastIndex = dataList.size();
        if (dataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex,list.size());
        }
    }

    public void clear() {
        dataList.clear();
        notifyDataSetChanged();
    }

    public void setData(ArrayList<JobFavorite> list) {
        dataList = list;
        notifyDataSetChanged();
    }


}

