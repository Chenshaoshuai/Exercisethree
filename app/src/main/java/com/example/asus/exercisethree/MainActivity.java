package com.example.asus.exercisethree;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.exercisethree.adapter.DetailsBannerAdapter;
import com.example.asus.exercisethree.entity.AddCarBean;
import com.example.asus.exercisethree.entity.DetailsBean;
import com.example.asus.exercisethree.entity.FindShoppingCarBean;
import com.example.asus.exercisethree.entity.ShoppingResultBean;
import com.example.asus.exercisethree.presenter.IPresent;
import com.example.asus.exercisethree.presenter.IPresentImpl;
import com.example.asus.exercisethree.view.IView;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements IView {
    private IPresentImpl iPresent;
    private TextView price;
    private TextView title;
    private Button addcar,accesscar;
    private ViewPager banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPresent=new IPresentImpl(this);
        iPresent.getRequest(Apis.DETAILS,DetailsBean.class);
        price = findViewById(R.id.price);
        title = findViewById(R.id.title);
        accesscar = findViewById(R.id.accesscar);
        addcar = findViewById(R.id.addcar);
        banner = findViewById(R.id.banner);
        addcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addShoppingCar();
            }
        });
        accesscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ShoppingActivity.class));
            }
        });



    }

    private void addShoppingCar() {
        iPresent.getRequest(Apis.QUERY_CAR,FindShoppingCarBean.class);
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof  DetailsBean){
            DetailsBean bean = (DetailsBean) data;
            DetailsBean.ResultBean resultBean = bean.getResult();
            String picture = resultBean.getPicture();
            Pattern compile = Pattern.compile("\\,");
            String[] split = compile.split(picture);
            price.setText("￥"+resultBean.getPrice()+"");
            title.setText(resultBean.getCommodityName());
            DetailsBannerAdapter adapter = new DetailsBannerAdapter(split,this);
            banner.setAdapter(adapter);
        }else if(data instanceof FindShoppingCarBean){
            FindShoppingCarBean findShoppingCarBean = (FindShoppingCarBean) data;
            if(findShoppingCarBean.getMessage().equals("查询成功")){
                List<ShoppingResultBean> list = new ArrayList<>();
                List<FindShoppingCarBean.ResultBean> result = findShoppingCarBean.getResult();
                for(FindShoppingCarBean.ResultBean resultBean:result){
                    list.add(new ShoppingResultBean(resultBean.getCommodityId(),resultBean.getCount()));
                }
                addShopping(list);
            }

        }else if(data instanceof AddCarBean){
            AddCarBean addCarBean = (AddCarBean) data;
            if(addCarBean.getMessage().equals("同步成功")){
                Toast.makeText(this, "已加入购物车,可点击查看", Toast.LENGTH_SHORT).show();
            }
        }



    }

    private void addShopping(List<ShoppingResultBean> list) {
        if(list.size()==0){
            list.add(new ShoppingResultBean(6,1));
        }else {
            for(int i=0;i<list.size();i++){
                if(6==list.get(i).getCommodityId()){
                    int count =list.get(i).getCount();
                    count++;
                    list.get(i).setCount(count);
                    break;
                }else if(i==list.size()-1){
                    list.add(new ShoppingResultBean(6,1));
                    break;
                }
            }
        }
        String s = new Gson().toJson(list);
        Map<String,String> map = new HashMap<>();
        map.put("data",s);
        iPresent.putRequest(Apis.ADDCAR,map,AddCarBean.class);
    }
}
