package com.example.airaccident.Other.LaoHuangLi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.airaccident.R;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;

public class TodayActivity extends AppCompatActivity implements View.OnClickListener {
    TextView today_header,today_number,today_week,today_lunar;
    TextView today_PengZu,today_WuXing,today_ChongSha,today_JiShen,today_XiongShen,today_yi,today_Ji;
    private ImageButton imgBtn;
    private Calendar calendar;
    private Date date;
    String todayurl;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);
        //绑定控件
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
        //设置点击事件
        imgBtn=findViewById(R.id.today_imgbtn);
        imgBtn.setOnClickListener((View.OnClickListener) this);

        //获取日历对象
        calendar= Calendar.getInstance();
        date=new Date();
        calendar.setTime(date);
        //将日期对象转换成指定格式的字符串形式
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String time=sdf.format(date);
        //获取请求的网址和key
        todayurl="http://v.juhe.cn/laohuangli/d";
        key="4d6cde4b010691d04a2d60c388b7ebe0";
        //请求数据
        loadHeaderData(todayurl,time,key);
    }

    private void loadHeaderData(String url,String time,String key){
        OkHttpUtils.post()
                .url(url)
                .addParams("date",time)
                .addParams("key",key)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            Log.e("请求的",response);
                            LaoHuangLiBean huangLiBean=new Gson().fromJson(response, LaoHuangLiBean.class);
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


                        }catch (Exception e)
                        {

                        }
                    }
                });
    }

    //根据年月日获取对应的星期
    private String getWeek(int year, int month, int day) {

        Calendar calendar=Calendar.getInstance();
        calendar.set(year,month-1,day);
        String weeks[]={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        int index=calendar.get(Calendar.DAY_OF_WEEK)-1;
        if(index<0){
            index=0;
        }
        return weeks[index];
    }

    //点击日期控件，弹出日期对话框
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.today_imgbtn) {
            popCalendarDialog();
            return;
        }
    }

    //弹出日期对话框，选择时间，改变老黄历
    private void popCalendarDialog() {

        Calendar calendar=Calendar.getInstance();
        DatePickerDialog dialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //改变老黄历上显示的内容
                String time=year+"-"+(month+1)+"-"+dayOfMonth;
                loadHeaderData(todayurl,time,key);
            }
        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

}
