package com.atguigu.guigushangcheng2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.guigushangcheng2.R;
import com.atguigu.guigushangcheng2.bean.HomeBean;
import com.atguigu.guigushangcheng2.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/12.
 */

public class RecommendGridViewAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<HomeBean.ResultBean.RecommendInfoBean> datas;

    public RecommendGridViewAdapter(Context mContext, List<HomeBean.ResultBean.RecommendInfoBean> recommend_info) {
        this.mContext = mContext;
        this.datas = recommend_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_recommend_grid_view, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //根据位置得到对应的数据
        HomeBean.ResultBean.RecommendInfoBean infoEntity = datas.get(position);
        //绑定数据
        viewHolder.tvName.setText(infoEntity.getName());
        viewHolder.tvPrice.setText("￥"+infoEntity.getCover_price());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+infoEntity.getFigure()).into(viewHolder.ivRecommend);


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.iv_recommend)
        ImageView ivRecommend;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

