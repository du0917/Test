package com.example.day02text;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.day02text.Adapter.VpAdapter;
import com.example.day02text.Fragment.CollectFragment;
import com.example.day02text.Fragment.MyFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.ViewPager);
        mTabLayout = (TabLayout) findViewById(R.id.TabLayout);
        mFragments = new ArrayList<>();
        mFragments.add(MyFragment.myFragment());
        mFragments.add(CollectFragment.myFragment());
        ArrayList<String> list = new ArrayList<>();
        list.add("我的");
        list.add("收藏");
        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), mFragments, list);
        mViewPager.setAdapter(vpAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position==1) {
                    CollectFragment fragment = (CollectFragment) mFragments.get(position);
                    fragment.getData();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
