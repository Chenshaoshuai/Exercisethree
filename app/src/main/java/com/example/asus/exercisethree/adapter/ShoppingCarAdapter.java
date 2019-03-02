package com.example.asus.exercisethree.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.exercisethree.R;
import com.example.asus.exercisethree.entity.FindShoppingCarBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCarAdapter extends RecyclerView.Adapter<ShoppingCarAdapter.ViewHolder> {
     private Context mContext;
     private List<FindShoppingCarBean.ResultBean> mData;

    public ShoppingCarAdapter(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate( R.layout.item_shopping,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
      viewHolder.image.setImageURI(Uri.parse(mData.get(i).getPic()));
      viewHolder.price.setText("￥"+mData.get(i).getPrice()+"");
      viewHolder.title.setText(mData.get(i).getCommodityName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(List<FindShoppingCarBean.ResultBean> result) {
        this.mData = result;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView image;
        private TextView title;
        private TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image =itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.shopping_title);
            price = itemView.findViewById(R.id.shopping_price);


        }
    }
}
