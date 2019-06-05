package com.jpan.fragmentpager;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

public class FragmentEntry {
    private Fragment fragment;

    private Drawable selectImage;

    private Drawable unSelectImage;

    private String title;

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Drawable getSelectImage() {
        return selectImage;
    }

    public void setSelectImage(Drawable selectImage) {
        this.selectImage = selectImage;
    }

    public Drawable getUnSelectImage() {
        return unSelectImage;
    }

    public void setUnSelectImage(Drawable unSelectImage) {
        this.unSelectImage = unSelectImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
