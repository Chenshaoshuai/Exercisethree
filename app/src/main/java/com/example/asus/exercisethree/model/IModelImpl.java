package com.example.asus.exercisethree.model;

import com.example.asus.exercisethree.R;
import com.example.asus.exercisethree.callback.MyCallback;
import com.example.asus.exercisethree.network.RetrofitMessage;
import com.google.gson.Gson;

import java.util.Map;

public class IModelImpl implements IModel {

    @Override
    public void getQuest(String url, final Class clazz, final MyCallback callback) {
        RetrofitMessage.getmRetrofitMessage().get(url, new RetrofitMessage.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                callback.onSuccess(o);
            }

            @Override
            public void onFails(String erroe) {
                callback.onSuccess(erroe);
            }
        });
    }

    @Override
    public void putQuest(String url, Map<String, String> params, final Class clazz, final MyCallback callback) {
        RetrofitMessage.getmRetrofitMessage().put(url, params, new RetrofitMessage.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object o = new Gson().fromJson(data,clazz);
                callback.onSuccess(o);
            }

            @Override
            public void onFails(String erroe) {
             callback.onSuccess(erroe);
            }
        });
    }
}
