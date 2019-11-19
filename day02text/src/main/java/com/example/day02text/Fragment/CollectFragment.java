package com.example.day02text.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day02text.Adapter.RecyAdapter;
import com.example.day02text.MyApplication;
import com.example.day02text.R;
import com.example.day02text.RecentBean;
import com.example.day02text.RecentBeanDao;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerView;
    private List<RecentBean> mRecentBeans1;
    private ArrayList<RecentBean> mRecentBeans;
    private RecyAdapter mRecyAdapter;
    private RecentBeanDao mRecentBeanDao;

    public static CollectFragment myFragment() {
        CollectFragment collectFragment = new CollectFragment();
        return collectFragment;
    }

    public CollectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_collect, container, false);
        initView(inflate);
        return inflate;

    }

    private void initView(View inflate) {
        mRecyclerView = (RecyclerView) inflate.findViewById(R.id.RecyclerView);
        mRecentBeans = new ArrayList<>();
        mRecyAdapter = new RecyAdapter(mRecentBeans, getContext());
        mRecentBeanDao = MyApplication.daoSession.getRecentBeanDao();
        mRecentBeans1 = mRecentBeanDao.loadAll();
        mRecentBeans.addAll(mRecentBeans1);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mRecyAdapter);
        mRecyAdapter.notifyDataSetChanged();
    }

    public void getData() {
        mRecentBeans.clear();
        mRecentBeans1 = mRecentBeanDao.loadAll();
        mRecentBeans.addAll(mRecentBeans1);
        mRecyAdapter.notifyDataSetChanged();
    }
}
