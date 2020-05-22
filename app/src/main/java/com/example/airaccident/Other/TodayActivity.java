package com.example.airaccident.Other;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.airaccident.Other.History.contentbase.ContentURL;
import com.example.airaccident.Other.History.hisbean.LaoHuangLiBean;
import com.example.airaccident.R;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TodayActivity extends AppCompatActivity {
    TextView today_header,today_number,today_week,today_lunar;
    TextView today_PengZu,today_WuXing,today_ChongSha,today_JiShen,today_XiongShen,today_yi,today_Ji;
    private Calendar calendar;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        today_header=findViewById(R.id.today_header);
        today_number=findViewById(R.id.today_number);
        today_week=findViewById(R.id.today_week);
        today_lunar=findViewById(R.id.today_lunar);
        today_PengZu=findViewById(R.id.today_PengZu);
        today_WuXing=findViewById(R.id.today_WuXing);
        today_ChongSha=findViewById(R.id.today_ChongSha);
        today_JiShen=findViewById(R.id.today_JiShen);
        today_XiongShen=findViewById(R.id.today_XiongShen);
        today_yi=findViewById(R.id.today_yi);
        today_Ji=findViewById(R.id.today_Ji);

        //获取日历对象
        calendar= Calendar.getInstance();
        date=new Date();
        calendar.setTime(date);
        //将日期对象转换成指定格式的字符串形式
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String time=sdf.format(date);
        //获取请求的网址
        String laohuangliURL= ContentURL.getLaoHuangLiURL(time);
        loadHeaderData(laohuangliURL);
    }

    private void loadHeaderData(String laohuangliURL) {
        //获取老黄历接口数据
        RequestParams params=new RequestParams(laohuangliURL);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LaoHuangLiBean huangLiBean=new Gson().fromJson(result, LaoHuangLiBean.class);
                LaoHuangLiBean.ResultBean resultBean=huangLiBean.getResult();
                today_lunar.setText("农历"+resultBean.getYinli()+"（阴历）");
                String[]yangliArr=resultBean.getYangli().split("-");
                String week=getWeek(Integer.parseInt(yangliArr[0]),Integer.parseInt(yangliArr[1]),Integer.parseInt(yangliArr[2]));
                today_header.setText("公历"+yangliArr[0]+"年"+yangliArr[1]+"月"+yangliArr[2]+"日"+week+"（阳历）");
                today_number.setText(yangliArr[2]);
                today_week.setText(week);
                today_PengZu.setText(resultBean.getBaiji());
                today_WuXing.setText(resultBean.getWuxing());
                today_ChongSha.setText(resultBean.getChongsha());
                today_JiShen.setText(resultBean.getJishen());
                today_XiongShen.setText(resultBean.getXiongshen());
                today_yi.setText(resultBean.getYi());
                today_Ji.setText(resultBean.getJi());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private String getWeek(int year, int month, int day) {
        //根据年月日获取对应的星期
        Calendar calendar=Calendar.getInstance();
        calendar.set(year,month-1,day);
        String weeks[]={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        int index=calendar.get(Calendar.DAY_OF_WEEK)-1;
        if(index<0){
            index=0;
        }
        return weeks[index];
    }



}
