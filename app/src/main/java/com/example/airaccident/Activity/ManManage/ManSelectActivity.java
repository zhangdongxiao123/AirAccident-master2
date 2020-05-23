package com.example.airaccident.Activity.ManManage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.airaccident.Activity.AccManage.ManAddActivity;
import com.example.airaccident.R;
import com.example.airaccident.Activity.ReaManage.ReasonChooseActivity;
import com.example.airaccident.Activity.AccManage.ManChooseActivity;
import com.example.airaccident.Activity.ReaManage.ReasonAddActivity;

public class ManSelectActivity extends AppCompatActivity implements View.OnClickListener{
    Button zengjia,genggai,zjyy,xgyy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_select);
        //1.绑定控件
        zengjia=findViewById(R.id.manSelect_zengjia);
        genggai=findViewById(R.id.manSelect_genggai);
        zjyy=findViewById(R.id.manSelect_zjyy);
        xgyy=findViewById(R.id.manSelect_xgyy);
        //2.设置点击事件
        zengjia.setOnClickListener(this);
        genggai.setOnClickListener(this);
        zjyy.setOnClickListener(this);
        xgyy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.manSelect_zengjia:
                Intent intent1=new Intent(this, ManAddActivity.class);
                startActivity(intent1);
                break;
            case R.id.manSelect_genggai:
                Intent intent2=new Intent(this, ManChooseActivity.class);
                startActivity(intent2);
                break;
            case R.id.manSelect_zjyy:
                Intent intent3=new Intent(this, ReasonAddActivity.class);
                startActivity(intent3);
                break;
            case R.id.manSelect_xgyy:
                Intent intent4=new Intent(this, ReasonChooseActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
