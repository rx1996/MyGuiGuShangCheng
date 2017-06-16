package com.atguigu.guigushangcheng2.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.atguigu.guigushangcheng2.R;
import com.atguigu.guigushangcheng2.adapter.TypeLeftAdapter;
import com.atguigu.guigushangcheng2.basefragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/6/16.
 */

public class ListFragment extends BaseFragment {
    @BindView(R.id.lv_left)
    ListView lvLeft;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;
    Unbinder unbinder;
    private TypeLeftAdapter typeLeftAdapter;
    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品",
            "办公文具", "数码周边", "游戏专区"};

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_list, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        typeLeftAdapter = new TypeLeftAdapter(mContext,titles);
        lvLeft.setAdapter(typeLeftAdapter);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
