package com.atguigu.guigushangcheng2.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.atguigu.guigushangcheng2.R;
import com.atguigu.guigushangcheng2.activity.MainActivity;
import com.atguigu.guigushangcheng2.adapter.CommunityViewPagerAdapter;
import com.atguigu.guigushangcheng2.basefragment.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/6/12.
 */

public class CommunityFragment extends BaseFragment {
    private static final String TAG = CommunityFragment.class.getSimpleName();//"CommunityFragment"
    @BindView(R.id.ib_community_icon)
    ImageButton ibCommunityIcon;
    @BindView(R.id.ib_community_message)
    ImageButton ibCommunityMessage;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    Unbinder unbinder;
    private ArrayList<BaseFragment> fragments;
    private CommunityViewPagerAdapter pagerAdapter;

    /**
     * 初始化控件
     * retur
     */
    @Override
    public View initView() {
        Log.e(TAG, "初始化社区控件...");
        View rootView = View.inflate(mContext, R.layout.fragment_community, null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "绑定数据到控件上...");
        fragments = new ArrayList<>();
        fragments.add(new NewPostFragment());
        fragments.add(new HotPostFragment());

        MainActivity mainActivity = (MainActivity) mContext;
        //设置适配器
        pagerAdapter = new CommunityViewPagerAdapter(mainActivity.getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        tablayout.setupWithViewPager(viewPager);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ib_community_icon, R.id.ib_community_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_community_icon:
                Toast.makeText(mContext, "图片", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_community_message:
                Toast.makeText(mContext, "消息", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}