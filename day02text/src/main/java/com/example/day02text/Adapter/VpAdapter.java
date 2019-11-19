package com.example.day02text.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by 雪碧 on 2019/11/19.
 */

public class VpAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTitle;

    public VpAdapter(FragmentManager fm, ArrayList<android.support.v4.app.Fragment> fragments, ArrayList<String> title) {
        super(fm);
        mFragments = fragments;
        mTitle = title;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
