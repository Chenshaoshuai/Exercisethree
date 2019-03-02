package com.example.asus.exercisethree.model;

import com.example.asus.exercisethree.callback.MyCallback;

import java.util.Map;

public interface IModel {
   void getQuest(String url, Class clazz, MyCallback callback);
   void putQuest(String url, Map<String,String> params, Class clazz, MyCallback callback);
}
