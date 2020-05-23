package com.example.airaccident.UserManage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.airaccident.R;
import com.knifestone.hyena.currency.InputFilterAdapter;
import com.knifestone.hyena.currency.TextWatcherAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

import static com.example.airaccident.app.Url.userUpdateUserInfo;

public class PasswordActivity extends AppCompatActivity {
    EditText acount,password,again;
    Button gengxin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        //初始化
        acount=(EditText)findViewById(R.id.password_account);
        password=(EditText)findViewById(R.id.password_pwd);
        again=findViewById(R.id.password_again);
        gengxin=(Button)findViewById(R.id.password_gengxin);
        //设置过滤，账号输入框只能输入字母、数字、符号、中文，过滤表情
        InputFilterAdapter inputFilter=new InputFilterAdapter
                .Builder()
                .filterEmoji(true)
                .builder();
        acount.setFilters(new InputFilter[]{inputFilter});
        //设置过滤，密码输入框只能输入字母、数字、符号，过滤表情和中文
        InputFilterAdapter inputFilter1=new InputFilterAdapter
                .Builder()
                .filterEmoji(true)
                .filterChinese(true)
                .builder();
        password.setFilters(new InputFilter[]{inputFilter1});
        again.setFilters(new InputFilter[]{inputFilter1});
        //设置文本变化监听
        acount.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                checkSubmit();

            }
        });
        password.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                checkSubmit();

            }
        });
        again.addTextChangedListener(new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                checkSubmit();

            }
        });
        initData();
    }
    //注册逻辑
    private void initData(){
     gengxin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if (!again.getText().toString().equals(password.getText().toString()))
             {
                 Toast.makeText(PasswordActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();

                 return;
             }
             OkHttpUtils.post()
                     .url(userUpdateUserInfo)
                     .addParams("useracct",acount.getText().toString().trim())
                     .addParams("userpwd",password.getText().toString().trim())
                     .build()
                     .execute(new StringCallback() {
                         @Override
                         public void onError(Call call, Exception e, int id) {

                         }

                         @Override
                         public void onResponse(String response, int id) {
                             try {

                                 JSONObject jsonObject = JSON.parseObject(response);
                                 String status = jsonObject.getString("status");
                                 String msg = jsonObject.getString("msg");

                                 if (status.equals("0"))
                                 {
                                     Toast.makeText(PasswordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                     finish();

                                 }

                                 else {
                                     Toast.makeText(PasswordActivity.this, msg+"", Toast.LENGTH_SHORT).show();

                                 }



                             }catch (Exception e)
                             {

                             }
                         }
                     });
         }
     });
    }



    /**
     * 第一次跳转到修改密码页面，按钮不能够后被点击
     */

    @Override
    protected void onResume() {
        super.onResume();
        checkSubmit();
    }

    /**
     * 检测是否可以提交
     */
    private void checkSubmit(){

        String msg = acount.getText().toString().trim();
        if(TextUtils.isEmpty(msg)){
            gengxin.setEnabled(false);
            return;
        }
        if(msg.length()>10){
            gengxin.setEnabled(false);
            return;
        }

        msg = password.getText().toString().trim();
        if(TextUtils.isEmpty(msg)){
            gengxin.setEnabled(false);
            return;
        }
        if(msg.length()>15){
            gengxin.setEnabled(false);
            return;
        }

        msg = again.getText().toString().trim();
        if(TextUtils.isEmpty(msg)){
            gengxin.setEnabled(false);
            return;
        }
        if(msg.length()>15){
            gengxin.setEnabled(false);
            return;
        }

        gengxin.setEnabled(true);
    }
}
