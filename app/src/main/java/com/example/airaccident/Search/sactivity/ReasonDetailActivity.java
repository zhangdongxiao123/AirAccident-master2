package com.example.airaccident.Search.sactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.airaccident.R;
import com.example.airaccident.bean.ReasonDetailBean;
import com.example.airaccident.bean.ReasonListBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import static com.example.airaccident.app.Url.delete;
import static com.example.airaccident.app.Url.detail;
import static com.example.airaccident.app.Url.detaildelete;
import static com.example.airaccident.app.Url.detailupdate;
import static com.example.airaccident.app.Url.reasonadd;

public class ReasonDetailActivity extends AppCompatActivity {
    EditText manadd_airname,manadd_airhow;

    TextView manadd_id;
    String reasonId = "";
    ImageView manChange_shanchu,manChange_xiugai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reason_detail);
        manChange_shanchu= findViewById(R.id.manChange_shanchu);
        manChange_xiugai = findViewById(R.id.manChange_xiugai);
        manadd_airname = findViewById(R.id.manadd_airname);
        manadd_airhow = findViewById(R.id.manadd_airhow);
        manadd_id = findViewById(R.id.manadd_id);
        Intent intent = getIntent();
        reasonId = intent.getStringExtra("reasonId");

        OkHttpUtils.get()
                .url(detail)
                .addParams("reasonId",reasonId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {


                        ReasonDetailBean reasonDetailBean =JSON.parseObject(response, ReasonDetailBean.class);

                        manadd_id.setText(reasonDetailBean.getData().getReaid());
                        manadd_airname.setText(reasonDetailBean.getData().getReaname());
                        manadd_airhow.setText(reasonDetailBean.getData().getReahow());

                    }
                });



        manChange_shanchu.setOnClickListener(view -> {
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("确定删除嘛?")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {


                            OkHttpUtils.post()
                                    .url(detaildelete)
                                    .addParams("reaid",reasonId)
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {

                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            try {
                                                Log.e("请求的",response);

                                                JSONObject jsonObject = JSON.parseObject(response);
                                                String status  = jsonObject.getString("status");
                                                String msg  = jsonObject.getString("msg");
                                                if (status.equals("0"))
                                                {
                                                    Toast.makeText(ReasonDetailActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                                    finish();
                                                }else {
                                                    Toast.makeText(ReasonDetailActivity.this, msg+"", Toast.LENGTH_SHORT).show();
                                                }

                                            }catch (Exception e)
                                            {

                                            }
                                        }
                                    });

                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    })
                    .show();
        });

        manChange_xiugai.setOnClickListener(view -> {

            if (manadd_airname.getText().toString().equals(""))
            {
                Toast.makeText(this, "请填写事故原因", Toast.LENGTH_SHORT).show();
                return;
            }
            if (manadd_airhow.getText().toString().equals(""))
            {
                Toast.makeText(this, "请填写事故详情", Toast.LENGTH_SHORT).show();
                return;
            }

            OkHttpUtils.post()
                    .url(detailupdate)
                    .addParams("reaid",reasonId)
                    .addParams("reaname",manadd_airname.getText().toString())
                    .addParams("reahow",manadd_airhow.getText().toString())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {

                        }

                        @Override
                        public void onResponse(String response, int id) {



                            try {
                                Log.e("请求的",response);
                                JSONObject jsonObject = JSON.parseObject(response);
                                String status  = jsonObject.getString("status");
                                String msg  = jsonObject.getString("msg");
                                if (status.equals("0"))
                                {
                                    Toast.makeText(ReasonDetailActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(ReasonDetailActivity.this, msg+"", Toast.LENGTH_SHORT).show();
                                }


                            }catch (Exception e)
                            {

                            }


                        }
                    });


        });
    }
}