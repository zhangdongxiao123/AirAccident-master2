package com.example.airaccident.Activity.AccSearch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.airaccident.R;

public class MultipleActivity extends AppCompatActivity implements View.OnClickListener {
    EditText name,type,when,where,why;
    Button duocha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple);
        //1.绑定控件
        name=findViewById(R.id.multiple_name);
        type=findViewById(R.id.multiple_type);
        when=findViewById(R.id.multiple_when);
        where=findViewById(R.id.multiple_where);
        why=findViewById(R.id.multiple_why);
        duocha=findViewById(R.id.multiple_duocha);
        //2.设置点击事件
        duocha.setOnClickListener(this);//查询

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.multiple_duocha:
                String airname = name.getText().toString().trim();
                String airtype = type.getText().toString().trim();
                String airwhen = when.getText().toString().trim();
                String airwhere = where.getText().toString().trim();
                String airwhy = why.getText().toString().trim();
                if (airname.length()>30)
                {
                    Toast.makeText(this, "事故名称长度不能大于30", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (airtype.length()>5)
                {
                    Toast.makeText(this, "事故类型长度不能大于5", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (airwhen.length()>11)
                {
                    Toast.makeText(this, "事故时间长度不能大于11", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (airwhere.length()>5)
                {
                    Toast.makeText(this, "事故地点长度不能大于5", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (airwhy.length()>10)
                {
                    Toast.makeText(this, "事故原因长度不能大于10", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent =new Intent(this, MultipleSecondActivity.class);
                intent.putExtra("airname",airname);
                intent.putExtra("airtype",airtype);
                intent.putExtra("airwhen",airwhen);
                intent.putExtra("airwhere",airwhere);
                intent.putExtra("airwhy",airwhy);
                startActivity(intent);
                break;
        }
    }
}
