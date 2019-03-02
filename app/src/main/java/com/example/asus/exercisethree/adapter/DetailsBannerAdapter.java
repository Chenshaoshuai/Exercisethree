package com.example.asus.exercisethree.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

public class DetailsBannerAdapter extends PagerAdapter {
    private String[] images;
    private Context mContext;

    public DetailsBannerAdapter(String[] images, Context mContext) {
        this.images = images;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SimpleDraweeView draweeView = new SimpleDraweeView(mContext);
        draweeView.setImageURI(images[position % images.length] );
        container.addView(draweeView);
        return draweeView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
