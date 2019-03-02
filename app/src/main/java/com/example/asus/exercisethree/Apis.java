package com.example.asus.exercisethree;

public class Apis {
    public static final String DETAILS="commodity/v1/findCommodityDetailsById?commodityId=6";

    public static final String ADDCAR="order/verify/v1/syncShoppingCart";
    //查询购物车
    public static final String QUERY_CAR="order/verify/v1/findShoppingCart?userId=%s&sessionId=%s";
}
