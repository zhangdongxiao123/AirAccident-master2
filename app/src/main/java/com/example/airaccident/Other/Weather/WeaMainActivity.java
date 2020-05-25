package com.example.airaccident.Other.Weather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.airaccident.R;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class WeaMainActivity extends AppCompatActivity implements View.OnClickListener {
    //各种控件
    EditText weather_shuru;
    ImageView weather_sousuo;
    TextView tempTv,citvTv,conditionTv,dateTv,windTv,tempRangeTv;
    ImageView dayIv;
    TextView clothIndexTv,carIndexTv,coldIndexTv,sportIndexTv,raysIndexTv;
    LinearLayout futureLayout;
    int count=0;//判断是不是第一次加载未来三天的信息
    private SharedPreferences preferences;//存储自己想要的天气信息
    private SharedPreferences.Editor editor;
    //获取的指数数据
    private List<weaBean.ResultsBean.IndexBean>indexList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wea_main);
        //一、绑定控件
        //1.搜索
        weather_shuru=findViewById(R.id.weather_shuru);//输入地名
        weather_sousuo=findViewById(R.id.weather_sousuo);//搜索天气
        //2.今日天气信息
        tempTv=findViewById(R.id.wea_tv_currenttemp);//当前温度
        citvTv=findViewById(R.id.wea_tv_city);//当前城市
        conditionTv=findViewById(R.id.wea_tv_condition);//当前天气状况
        dateTv=findViewById(R.id.wea_tv_date);//当前日期
        windTv=findViewById(R.id.wea_tv_wind);//当前风向
        tempRangeTv=findViewById(R.id.wea_tv_temprange);//今日气温变化范围
        dayIv=findViewById(R.id.wea_iv_today);//当日天气图片
        //3.未来三天
        futureLayout=findViewById(R.id.wea_center_layout);
        //4.指数信息
        clothIndexTv=findViewById(R.id.wea_index_tv_dress);//穿衣指数
        carIndexTv=findViewById(R.id.wea_index_tv_washcar);//洗车指数
        coldIndexTv=findViewById(R.id.wea_index_tv_cold);//感冒指数
        sportIndexTv=findViewById(R.id.wea_index_tv_sport);//运动指数
        raysIndexTv=findViewById(R.id.wea_index_tv_rays);//紫外线指数
        //二、监听点击事件
        weather_sousuo.setOnClickListener(this);//搜索
        clothIndexTv.setOnClickListener(this);//穿衣指数
        carIndexTv.setOnClickListener(this);//洗车指数
        coldIndexTv.setOnClickListener(this);//感冒指数
        sportIndexTv.setOnClickListener(this);//运动指数
        raysIndexTv.setOnClickListener(this);//紫外线指数
        //存储地点信息
        preferences=getSharedPreferences("weather",MODE_PRIVATE);
        editor = preferences.edit();
        //三、天气url
        String urla="http://api.map.baidu.com/telematics/v3/weather?location=";
        String urlb=preferences.getString("city","天津");
        String urlc="&output=json&ak=FkPhtMBK0HTIQNh7gG4cNUttSTyr0nzo";
        //存储地点信息
        preferences=getSharedPreferences("weather",MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("city",urlb);
        editor.commit();
        //请求天气信息
        loadWeatherData(urla,urlb,urlc);


    }

    private void loadWeatherData(String urla, String urlb,  String urlc) {
        OkHttpUtils.get()
                .url(urla+urlb+urlc)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            Log.e("请求的",response);
                            //使用gson解析数据
                            weaBean weatherBean=new Gson().fromJson(response,weaBean.class);
                            int error=weatherBean.getError();
                            if(error!=0){
                                Toast.makeText(WeaMainActivity.this, "城市不存在", Toast.LENGTH_SHORT).show();
                                String url1="http://api.map.baidu.com/telematics/v3/weather?location=";
                                String url2=preferences.getString("city","天津");
                                String url3="&output=json&ak=FkPhtMBK0HTIQNh7gG4cNUttSTyr0nzo";
                                loadWeatherData(url1,url2,url3);
                            }else {
                                weaBean.ResultsBean resultsBean=weatherBean.getResults().get(0);
                                //获取数据成功
                                editor.putString("city",resultsBean.getCurrentCity());
                                editor.commit();
                                //获取指数信息集合列表
                                indexList=resultsBean.getIndex();
                                //设置TextView
                                dateTv.setText(weatherBean.getDate());
                                citvTv.setText(resultsBean.getCurrentCity());
                                //获取今天的天气情况
                                weaBean.ResultsBean.WeatherDataBean todayDataBean=resultsBean.getWeather_data().get(0);
                                windTv.setText(todayDataBean.getWind());
                                tempRangeTv.setText(todayDataBean.getTemperature());
                                conditionTv.setText(todayDataBean.getWeather());
                                //获取实时天气温度情况，需要处理字符串
                                String[] split=todayDataBean.getDate().split("：");
                                String todayTemp = split[1].replace(")","");
                                tempTv.setText(todayTemp);
                                //设置显示天气情况的图片
                                Glide.with(WeaMainActivity.this)
                                        .load(todayDataBean.getDayPictureUrl())
                                        .into(dayIv);
                                //获取未来三天的天气情况，加载到layout当中
                                List<weaBean.ResultsBean.WeatherDataBean> futureList=resultsBean.getWeather_data();
                                futureList.remove(0);//移除当天天气情况
                                if(count!=0){
                                    futureLayout.removeAllViews();
                                }
                                for(int i=0;i<futureList.size();i++) {
                                    View itemView = LayoutInflater.from(WeaMainActivity.this).inflate(R.layout.item_weather_center, null);
                                    itemView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                    futureLayout.addView(itemView);
                                    TextView idateTv = itemView.findViewById(R.id.item_center_tv_date);
                                    TextView iconTv = itemView.findViewById(R.id.item_center_tv_con);
                                    TextView itemprangeTv = itemView.findViewById(R.id.item_center_tv_temp);
                                    ImageView iIv = itemView.findViewById(R.id.item_center_iv);
                                    //获取对应位置的天气情况
                                    weaBean.ResultsBean.WeatherDataBean dataBean = futureList.get(i);
                                    idateTv.setText(dataBean.getDate());
                                    iconTv.setText(dataBean.getWeather());
                                    itemprangeTv.setText(dataBean.getTemperature());
                                    Glide.with(WeaMainActivity.this)
                                            .load(dataBean.getDayPictureUrl())
                                            .into(iIv);
                                }
                            }
                        }catch (Exception e) {

                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder=new AlertDialog.Builder(WeaMainActivity.this);
        switch (v.getId()) {
            case R.id.weather_sousuo:

                String url2=weather_shuru.getText().toString().trim();
                if (url2.equals(""))
                {
                    Toast.makeText(this, "请输入地名", Toast.LENGTH_SHORT).show();
                    return;
                }
                //摘要长度不能大于10
                String msg1=weather_shuru.getText().toString().trim();
                if(msg1.length()>12){
                    Toast.makeText(this,"输入地名长度不能大于10！", Toast.LENGTH_SHORT).show();
                    return;
                }
                String url1="http://api.map.baidu.com/telematics/v3/weather?location=";
                String url3="&output=json&ak=FkPhtMBK0HTIQNh7gG4cNUttSTyr0nzo";
                loadWeatherData(url1,url2,url3);
                count=1;
                break;
            case R.id.wea_index_tv_dress:
                builder.setTitle("穿衣指数");
                weaBean.ResultsBean.IndexBean indexBean=indexList.get(0);
                String msg=indexBean.getZs()+"\n"+indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.wea_index_tv_washcar:
                builder.setTitle("洗车指数");
                indexBean=indexList.get(1);
                msg=indexBean.getZs()+"\n"+indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.wea_index_tv_cold:
                builder.setTitle("感冒指数");
                indexBean=indexList.get(2);
                msg=indexBean.getZs()+"\n"+indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.wea_index_tv_sport:
                builder.setTitle("运动指数");
                indexBean=indexList.get(3);
                msg=indexBean.getZs()+"\n"+indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
            case R.id.wea_index_tv_rays:
                builder.setTitle("紫外线指数");
                indexBean=indexList.get(4);
                msg=indexBean.getZs()+"\n"+indexBean.getDes();
                builder.setMessage(msg);
                builder.setPositiveButton("确定",null);
                break;
        }
        builder.create().show();
    }
}
