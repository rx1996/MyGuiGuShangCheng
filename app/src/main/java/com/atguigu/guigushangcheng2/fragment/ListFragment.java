package com.atguigu.guigushangcheng2.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.atguigu.guigushangcheng2.R;
import com.atguigu.guigushangcheng2.adapter.TypeLeftAdapter;
import com.atguigu.guigushangcheng2.adapter.TypeRightAdapter;
import com.atguigu.guigushangcheng2.basefragment.BaseFragment;
import com.atguigu.guigushangcheng2.bean.TypeBean;
import com.atguigu.guigushangcheng2.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/16.
 */

public class ListFragment extends BaseFragment {
    private static final String TAG = ListFragment.class.getSimpleName();
    @BindView(R.id.lv_left)
    ListView lvLeft;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;
    Unbinder unbinder;
    private TypeLeftAdapter typeLeftAdapter;
    private String[] titles = new String[]{"小裙子", "上衣", "下装", "外套", "配件", "包包", "装扮", "居家宅品",
            "办公文具", "数码周边", "游戏专区"};

    private String[] urls = new String[]{Constants.SKIRT_URL, Constants.JACKET_URL, Constants.PANTS_URL, Constants.OVERCOAT_URL,
            Constants.ACCESSORY_URL, Constants.BAG_URL, Constants.DRESS_UP_URL, Constants.HOME_PRODUCTS_URL, Constants.STATIONERY_URL,
            Constants.DIGIT_URL, Constants.GAME_URL};
    /**
     * 右侧的数据
     */
    private List<TypeBean.ResultBean> result;
    private TypeRightAdapter rightAdapter;
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
        //设置监听点击ListView的item的点击事件，并且点击的时候变效果
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //1.记录点击的位置
                typeLeftAdapter.changeSelectPosition(position);
                //2.适配器刷新
                typeLeftAdapter.notifyDataSetChanged();//getView
                //联网请求
                getDataFromNet(urls[position]);
            }
        });
        getDataFromNet(urls[0]);
    }

    private void getDataFromNet(String url) {
        System.out.println("url=="+url);
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
            Log.e(TAG, "请求成功=="+response);
            processData(response);

        }
    }

    private void processData(String json) {
        TypeBean typeBean = JSON.parseObject(json,TypeBean.class);

        Log.e("TAG","解析成功=="+ typeBean.getResult().get(0).getName());
        result = typeBean.getResult();
        if(result != null && result.size() >0){
            //有数据
            //设置适配器
            rightAdapter = new TypeRightAdapter(mContext,result);
            rvRight.setAdapter(rightAdapter);

            //设置布局管理器
            GridLayoutManager gridLayout = new GridLayoutManager(mContext,3);
            gridLayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position ==0){
                        return 3;
                    }else{
                        return 1;
                    }
                }
            });
            rvRight.setLayoutManager(gridLayout);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
