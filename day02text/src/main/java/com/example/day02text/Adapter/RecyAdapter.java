package com.example.day02text.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day02text.Bean;
import com.example.day02text.R;
import com.example.day02text.RecentBean;

import java.util.ArrayList;

/**
 * Created by 雪碧 on 2019/11/19.
 */

public class RecyAdapter extends RecyclerView.Adapter {
    private ArrayList<RecentBean> mRecentBeans;
    private Context mContext;

    public RecyAdapter(ArrayList<RecentBean> recentBeans, Context context) {
        mRecentBeans = recentBeans;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        viewHolder.mTvTitle.setText(mRecentBeans.get(position).getTitle());
        Glide.with(mContext).load(mRecentBeans.get(position).getThumbnail()).into(viewHolder.mImUrl);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (mOnClickListener!=null){
                mOnClickListener.onClick(position);
            }
            }
        });
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnLongClickListener!=null){
                    mOnLongClickListener.onClick(position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecentBeans.size();
    }

    public void getData(Bean bean) {
        mRecentBeans.addAll(bean.getRecent());
        notifyDataSetChanged();
    }

    static class ViewHolder  extends RecyclerView.ViewHolder{
        View view;
        ImageView mImUrl;
        TextView mTvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mImUrl = (ImageView) itemView.findViewById(R.id.im_url);
            this.mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
    public interface onClickListener{
        void onClick (int position);

    }
    public onClickListener mOnClickListener;
    public onClickListener mOnLongClickListener;

    public void setOnClickListener(onClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public void setOnLongClickListener(onClickListener onLongClickListener) {
        mOnLongClickListener = onLongClickListener;
    }
}
