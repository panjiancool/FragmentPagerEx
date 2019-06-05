package com.jpan.fragmentpager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class FixedFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<FragmentEntry> mFragmentEntryList = null;

    public FixedFragmentPagerAdapter(FragmentManager fm, List<FragmentEntry> fragmentEntries) {
        super(fm);
        this.mFragmentEntryList = fragmentEntries;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentEntryList.get(i).getFragment();
    }

    @Override
    public int getCount() {
        return mFragmentEntryList == null ? 0 : mFragmentEntryList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (mFragmentEntryList != null && mFragmentEntryList.size() == getCount()) {
            return mFragmentEntryList.get(position).getTitle();
        }
        return "";
    }
}
