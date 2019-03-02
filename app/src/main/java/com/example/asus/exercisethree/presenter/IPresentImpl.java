package com.example.asus.exercisethree.presenter;

import com.example.asus.exercisethree.callback.MyCallback;
import com.example.asus.exercisethree.model.IModelImpl;
import com.example.asus.exercisethree.view.IView;

import java.util.Map;

public class IPresentImpl implements IPresent {
    private IModelImpl iModel;
    private IView iView;

    public IPresentImpl(IView iView) {
        this.iView = iView;
        iModel= new IModelImpl();
    }

    @Override
    public void getRequest(String url, Class clazz) {
       iModel.getQuest(url, clazz, new MyCallback() {
           @Override
           public void onSuccess(Object data) {
               iView.onSuccess(data);
           }
       });
    }

    @Override
    public void putRequest(String url, Map<String, String> params, Class clazz) {
        iModel.putQuest(url, params, clazz, new MyCallback() {
            @Override
            public void onSuccess(Object data) {
                iView.onSuccess(data);
            }
        });
    }
}
