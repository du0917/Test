package com.example.day02text.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.day02text.Adapter.RecyAdapter;
import com.example.day02text.Bean;
import com.example.day02text.MyApplication;
import com.example.day02text.R;
import com.example.day02text.RecentBean;
import com.example.day02text.WebActivity;
import com.example.day02text.presenter.MainPresenter;
import com.example.day02text.view.MainView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements MainView {
    private View view;
    private RecyclerView mRecyclerView;
    private MainPresenter mMainPresenter;
    private RecyAdapter mRecyAdapter;
    private ArrayList<RecentBean> mRecentBeans;
    private FrameLayout mF;

    public static MyFragment myFragment() {
        MyFragment myFragment = new MyFragment();
        return myFragment;
    }

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_my, container, false);
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.getData();
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mF = (FrameLayout) inflate.findViewById(R.id.f);
        mRecyclerView = (RecyclerView) inflate.findViewById(R.id.RecyclerView);
        mRecentBeans = new ArrayList<>();
        mRecyAdapter = new RecyAdapter(mRecentBeans, getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mRecyAdapter);
        mRecyAdapter.setOnClickListener(new RecyAdapter.onClickListener() {
            @Override
            public void onClick(int position) {
                String url = mRecentBeans.get(position).getUrl();
                EventBus.getDefault().postSticky(url);
                startActivity(new Intent(getContext(), WebActivity.class));
            }

        });
        mRecyAdapter.setOnLongClickListener(new RecyAdapter.onClickListener() {
            @Override
            public void onClick(final int position) {
                View popu = LayoutInflater.from(getContext()).inflate(R.layout.layout_popu, null);
                Button yes = popu.findViewById(R.id.yes);
                Button no = popu.findViewById(R.id.no);
                final PopupWindow popupWindow = new PopupWindow(popu, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(mF, Gravity.CENTER,0,0);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MyApplication.daoSession.getRecentBeanDao().insertOrReplace(mRecentBeans.get(position));
                        Toast.makeText(getContext(), "插入成功", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();

                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }

        });



    }

    @Override
    public void setData(Bean bean) {
//        mRecentBeans.addAll(bean)
        mRecyAdapter.getData(bean);
    }
//
//    @Override
//    public void setData(RecentBean bean) {
////        mRecentBeans.addAll(bean)
//        mRecyAdapter.getData(bean);
//    }
}
