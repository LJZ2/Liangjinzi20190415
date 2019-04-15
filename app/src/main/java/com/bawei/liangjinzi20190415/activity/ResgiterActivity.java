package com.bawei.liangjinzi20190415.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.liangjinzi20190415.R;
import com.bawei.liangjinzi20190415.bean.UserBean;
import com.bawei.liangjinzi20190415.mvp.model.MainModel;
import com.bawei.liangjinzi20190415.mvp.model.MainModellml;
import com.bawei.liangjinzi20190415.mvp.persenter.MainPersenter;
import com.bawei.liangjinzi20190415.mvp.persenter.MainPersenterlml;
import com.bawei.liangjinzi20190415.mvp.view.MainView;
import com.google.gson.Gson;

public class ResgiterActivity extends AppCompatActivity implements View.OnClickListener, MainView {

    private EditText username;
    private EditText password;
    private EditText password2;
    private TextView tv_login;
    private Button resgiter;
    private String name;
    private String pass;
    private String pass2;
    private MainPersenterlml mainPersenterlml;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgiter);
//        初始化
        initview();

        mainPersenterlml = new MainPersenterlml(new MainModellml(), this);
        sp = getSharedPreferences("user", MODE_PRIVATE);
    }

    private void initview() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        tv_login = findViewById(R.id.tv_login);
        resgiter = findViewById(R.id.resgiter);

        tv_login.setOnClickListener(this);
        resgiter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login://立即登录
                finish();
                break;
            case R.id.resgiter://注册
                doResgiter();
                break;
        }
    }
//    注册
    private void doResgiter() {
        name = username.getText().toString();
        pass = password.getText().toString();
        if (TextUtils.isEmpty(name)){
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_LONG).show();
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_LONG).show();
        }

//        走注册
       mainPersenterlml.doRegister(name,pass);
    }

    @Override
    public void success(String data) {
        UserBean bean = new Gson().fromJson(data, UserBean.class);
        sp.edit().putString("username",bean.getResult().getPhone())
                .putString("password",bean.getResult().getHeadPic());
        finish();
    }

    @Override
    public void fail(String error) {
        Toast.makeText(ResgiterActivity.this,"注册失败",Toast.LENGTH_LONG).show();
    }
}
