package com.atguigu.guigushangcheng2.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.guigushangcheng2.basefragment.BaseFragment;

/**
 * Created by Administrator on 2017/6/16.
 */

public class ListFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("分类Fragment的内容");
    }
}
