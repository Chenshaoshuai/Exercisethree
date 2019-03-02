package com.example.asus.exercisethree;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.asus.exercisethree.adapter.ShoppingCarAdapter;
import com.example.asus.exercisethree.entity.FindShoppingCarBean;
import com.example.asus.exercisethree.presenter.IPresentImpl;
import com.example.asus.exercisethree.view.IView;

public class ShoppingActivity extends AppCompatActivity implements IView {
    private IPresentImpl iPresent;
    private RecyclerView recyclerView;
    private ShoppingCarAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        recyclerView = findViewById(R.id.recycleshopping);
        iPresent = new IPresentImpl(this);
        iPresent.getRequest(Apis.QUERY_CAR,FindShoppingCarBean.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ShoppingCarAdapter(this);
        recyclerView.setAdapter(adapter);



    }

    @Override
    public void onSuccess(Object data) {
        FindShoppingCarBean findShoppingCarBean = (FindShoppingCarBean) data;
        adapter.setData(findShoppingCarBean.getResult());


    }
}
