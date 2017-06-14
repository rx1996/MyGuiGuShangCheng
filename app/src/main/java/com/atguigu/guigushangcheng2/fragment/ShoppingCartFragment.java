package com.atguigu.guigushangcheng2.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.guigushangcheng2.app.MyApplication;
import com.atguigu.guigushangcheng2.basefragment.BaseFragment;
import com.atguigu.guigushangcheng2.bean.GoodsBean;
import com.atguigu.guigushangcheng2.utils.CartStorage;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/12.
 */

public class ShoppingCartFragment extends BaseFragment {
    private static final String TAG = ShoppingCartFragment.class.getSimpleName();//"TypeFragment"
    private TextView textView;

    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        Log.e(TAG,"初始化购物车控件...");
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG,"绑定数据到控件上...");
        textView.setText("我是购物车内容");
        ArrayList<GoodsBean> allData = CartStorage.getInstance(MyApplication.getContext()).getAllData();
        for(int i = 0; i < allData.size(); i++) {
            Log.e("TAG",""+allData.get(i).toString());
        }
    }
}
