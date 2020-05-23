package com.example.airaccident.Activity.ReaManage;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.airaccident.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import static com.example.airaccident.AppConfigure.network.Url.reasonadd;

public class ReasonAddActivity extends AppCompatActivity {
    EditText manadd_airname,manadd_airhow;
    Button manadd_tianjia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reason_add);
        initView();
        initData();
    }


    private void initView() {
        manadd_airname = findViewById(R.id.manadd_airname);
        manadd_airhow = findViewById(R.id.manadd_airhow);
        manadd_tianjia = findViewById(R.id.manadd_tianjia);
    }


    private void initData() {
        manadd_tianjia.setOnClickListener(view -> {

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
                    .url(reasonadd)
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
                                    Toast.makeText(ReasonAddActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(ReasonAddActivity.this, msg+"", Toast.LENGTH_SHORT).show();
                                }


                            }catch (Exception e)
                            {

                            }


                        }
                    });

        });

    }


}
