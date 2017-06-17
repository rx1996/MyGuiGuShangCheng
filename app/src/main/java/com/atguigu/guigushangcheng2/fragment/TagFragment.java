package com.atguigu.guigushangcheng2.fragment;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.atguigu.guigushangcheng2.R;
import com.atguigu.guigushangcheng2.adapter.TagGridViewAdapter;
import com.atguigu.guigushangcheng2.basefragment.BaseFragment;
import com.atguigu.guigushangcheng2.bean.TagBean;
import com.atguigu.guigushangcheng2.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/16.
 */

public class TagFragment extends BaseFragment {
    @BindView(R.id.gv_tag)
    GridView gvTag;
    Unbinder unbinder;
    private TagGridViewAdapter adapter;
    private String TAG = TagFragment.class.getSimpleName();

    @Override
    public View initView() {
        View rootView = View.inflate(mContext, R.layout.fragment_tag, null);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet(Constants.TAG_URL);
    }

    private void getDataFromNet(String url) {
        System.out.println("url==" + url);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }

    class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e(TAG, "请求成功失败==" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e(TAG, "TAG请求成功==" );
            processData(response);

        }
    }

    private void processData(String json) {
        TagBean tagBean = JSON.parseObject(json,TagBean.class);
        Log.e("TAG",tagBean.getResult().get(0).getName());

        adapter = new TagGridViewAdapter(mContext,tagBean.getResult());
        gvTag.setAdapter(adapter);

        //设置item的点击事件
        gvTag.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TagBean.ResultBean item = adapter.getItem(position);
                Toast.makeText(mContext, ""+item.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        ;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
