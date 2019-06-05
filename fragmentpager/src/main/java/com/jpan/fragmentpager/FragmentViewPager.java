package com.jpan.fragmentpager;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

public class FragmentViewPager extends RelativeLayout implements ViewPager.OnPageChangeListener,
        BottomTabButton.BottomTabBtnListener {

    private ViewPager mViewPager;
    private LinearLayout mLayoutNavigation;
    private FixedFragmentPagerAdapter mAdapter;
    private Context context;
    private BottomTabButton[] tabButtons;
    private int mCurrentPos = 0;

    public FragmentViewPager(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    public FragmentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
    }

    public FragmentViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.layout_fragment_viewpager, this);
        mViewPager = view.findViewById(R.id.view_pager);
        mLayoutNavigation = view.findViewById(R.id.layout_bottom_navigation);
    }

    public void initViewPager(FragmentManager fm, List<FragmentEntry> fragmentEntryList) {
        initBottomTab(fragmentEntryList);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(fragmentEntryList.size() - 1);
        mAdapter = new FixedFragmentPagerAdapter(fm, fragmentEntryList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mCurrentPos);
        onPageSelected(mViewPager.getCurrentItem());
        tabButtons[mCurrentPos].setSelectedButton(true);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        tabButtons[mCurrentPos].setSelectedButton(false);
        tabButtons[i].setSelectedButton(true);
        mCurrentPos = i;
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private void initBottomTab(List<FragmentEntry> fragmentEntryList) {
        if (fragmentEntryList != null && !fragmentEntryList.isEmpty()) {
            int size = fragmentEntryList.size();
            tabButtons = new BottomTabButton[size];
            for (int i = 0; i < size; i++) {
                FragmentEntry entry = fragmentEntryList.get(i);
                BottomTabButton tabButton = new BottomTabButton(context);
                LinearLayout.LayoutParams layoutParams =
                        new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT , 1);
                tabButton.setTitle(entry.getTitle());
                tabButton.setSelectedImage(entry.getSelectImage());
                tabButton.setUnselectedImage(entry.getUnSelectImage());
                tabButton.setSelectedButton(false);
                tabButton.setIndex(this, i);
                tabButton.setLayoutParams(layoutParams);
                tabButtons[i] = tabButton;
                mLayoutNavigation.addView(tabButton);
            }
        }
    }

    @Override
    public void onSelectBottomTab(int index) {
        mViewPager.setCurrentItem(index);
        onPageSelected(index);
    }
}
