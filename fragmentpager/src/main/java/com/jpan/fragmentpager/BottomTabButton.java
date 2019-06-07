package com.jpan.fragmentpager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author GaryPan
 * @date 2019/6/5 - 17:45
 */
public class BottomTabButton extends FrameLayout implements View.OnClickListener {
    private int mIndex;
    private BottomTabBtnListener listener;

    private ImageView mImage; // 图标
    private TextView mTitle; // 标题
    private TextView mNotify; // 提示数

    private Drawable mSelectedImg; // 选中图标
    private Drawable mUnselectedImg; // 未选中图标

    public BottomTabButton(Context context) {
        this(context, null);
    }

    public BottomTabButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.widget_button_tab, this);
        mImage = view.findViewById(R.id.tab_img);
        mTitle = view.findViewById(R.id.tab_btn_title);
        mNotify = view.findViewById(R.id.tab_unread_notify_num);

        setOnClickListener(this);
    }

    public interface BottomTabBtnListener {
        void onSelectBottomTab(int index);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onSelectBottomTab(mIndex);
        }
    }

    /**
     * 设置 位置索引
     */
    public void setIndex(BottomTabBtnListener listener, int index) {
        this.listener = listener;
        this.mIndex = index;
    }

    public void setUnselectedImage(Drawable img) {
        this.mUnselectedImg = img;
    }

    public void setSelectedImage(Drawable img) {
        this.mSelectedImg = img;
    }

    private void setSelectedColor(Boolean selected) {
        if (selected) {
            mTitle.setTextColor(getResources().getColor(R.color.select_bg_color));
        } else {
            mTitle.setTextColor(getResources().getColor(R.color.unselect_bg_color));
        }
    }

    public void setSelectedButton(Boolean selected) {
        setSelectedColor(selected);
        if (selected) {
            mImage.setImageDrawable(mSelectedImg);
        } else {
            mImage.setImageDrawable(mUnselectedImg);
        }
    }

    /**
     * 标题
     *
     * @param title
     */
    public void setTitle(String title) {
        mTitle.setText(title);
    }

    /**
     * 设置未读数
     */
    public void setUnreadNotify(int unreadNum) {
        if (0 == unreadNum) {
            mNotify.setVisibility(View.INVISIBLE);
            return;
        }

        String notify;
        if (unreadNum > 99) {
            notify = "99+";
        } else {
            notify = Integer.toString(unreadNum);
        }
        mNotify.setText(notify);
        mNotify.setVisibility(View.VISIBLE);
    }
}
