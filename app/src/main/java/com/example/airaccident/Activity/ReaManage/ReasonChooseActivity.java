package com.example.airaccident.Activity.ReaManage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.airaccident.Adapter.ReasonChooseAdapter;
import com.example.airaccident.R;
import com.example.airaccident.bean.ReasonListBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import static com.example.airaccident.AppConfigure.network.Url.newsreasonsingleSelect;

public class ReasonChooseActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView recyclerview;
    ImageView shuaxin,sousuo;
    EditText shuru;
    RecyclerView liebiao;
    ReasonListBean reasonListBean;
    List<ReasonListBean.DataBean> list;
    ReasonChooseAdapter reasonChooseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_reason);
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerview);
        shuaxin=findViewById(R.id.reason_shuaxin);//刷新
        sousuo=findViewById(R.id.reason_sousuo);//搜索
        shuru=findViewById(R.id.reason_shuru);//输入
        liebiao=findViewById(R.id.recyclerview);//列表
        //初始化适配器
        list = new ArrayList<>();
        reasonChooseAdapter = new ReasonChooseAdapter(this,list);
        //设置RecyclerView为纵向布局
        liebiao.setLayoutManager(new LinearLayoutManager(this));
        //给列表设置适配器
        liebiao.setAdapter(reasonChooseAdapter);
        //2.设置点击事件
        shuaxin.setOnClickListener(this);
        sousuo.setOnClickListener(this);
        //传入关键字，初始值为空
        getok("");

    }

    private void getok(String keyword) {
        //get方法
        OkHttpUtils.get()
                .url(newsreasonsingleSelect+"?keyword="+keyword)
                //.addParams("",)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            Log.e("请求的",response);
                            //将Json转换成对应的JavaBean对象
                            reasonListBean  = JSON.parseObject(response, ReasonListBean.class);
                            //如果请求到的集合不为空
                            if (reasonListBean.getData().size()!=0)
                            {
                                //清空当前集合
                                list.clear();
                                //将新的集合添加进去
                                list.addAll(reasonListBean.getData());
                                //提示适配器更新
                                reasonChooseAdapter.notifyDataSetChanged();
                            }else {
                                //清空当前集合
                                list.clear();
                                //提示适配器更新
                                reasonChooseAdapter.notifyDataSetChanged();
                            }

                        }catch (Exception e)
                        {

                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击刷新，清空输入框中的内容，刷新界面
            case R.id.reason_shuaxin:
                shuru.setText("");
                getok("");

//                list.clear();
//                singleAdapter.notifyDataSetChanged();
                break;
            //点击搜索
            case R.id.reason_sousuo:
                String keyword = shuru.getText().toString();
                if (keyword.equals(""))
                {
                    Toast.makeText(this, "请输入关键词", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (keyword.length()>12)
                {
                    Toast.makeText(this, "关键词长度不能大于12", Toast.LENGTH_SHORT).show();
                    return;
                }
                //搜索请求事件
                getok(keyword);
                break;
        }
    }
}
