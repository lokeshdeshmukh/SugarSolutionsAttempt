package com.sugar.example.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.sugar.example.R;
import com.sugar.example.model.pojo.Images;

import java.util.ArrayList;

public  class ViewPagerAdapter extends PagerAdapter {

        ArrayList<Images> viewPagerUrl=new ArrayList<>();
        Context mContext;
        LayoutInflater mLayoutInflater;

        public ViewPagerAdapter(Context context,ArrayList<Images> viewPagerUrl) {
            this.viewPagerUrl=viewPagerUrl;
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return viewPagerUrl.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            Picasso.get().load("https://cdn.shopify.com/s/files/1/0906/2558/products/BTWGW02_01-compressor.png?v=1543578611").into(imageView);

//            Picasso.get().load(viewPagerUrl.get(position).getUrlImage()).resize(400,400).into(imageView);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
