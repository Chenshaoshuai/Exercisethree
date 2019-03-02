package com.example.asus.exercisethree.presenter;

import java.util.Map;

public interface IPresent {
    void getRequest(String url,Class clazz);
    void putRequest(String url, Map<String,String> params, Class clazz);
}
