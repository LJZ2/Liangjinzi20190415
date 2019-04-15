package com.bawei.liangjinzi20190415;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.liangjinzi20190415.activity.ResgiterActivity;
import com.bawei.liangjinzi20190415.activity.ShowActivity;
import com.bawei.liangjinzi20190415.bean.UserInBean;
import com.bawei.liangjinzi20190415.mvp.model.MainModellml;
import com.bawei.liangjinzi20190415.mvp.persenter.MainPersenterlml;
import com.bawei.liangjinzi20190415.mvp.view.MainView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainView {

    private EditText username;
    private EditText password;
    private TextView resgiter;
    private Button login;
    private MainPersenterlml mainPersenterlml;
    private String name;
    private String pass;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        初始化
        initview();

        mainPersenterlml = new MainPersenterlml(new MainModellml(), this);

        sp = getSharedPreferences("user", MODE_PRIVATE);
    }

    private void initview() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        resgiter = findViewById(R.id.tv_resgiter);
        login = findViewById(R.id.login);

        resgiter.setOnClickListener(this);
        login.setOnClickListener(this);
    }
//    内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPersenterlml.onstory();
    }

    //    点击
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_resgiter://立即注册
                startActivity(new Intent(MainActivity.this,ResgiterActivity.class));
                break;
            case R.id.login://登录
                doLogin();
                break;
        }
    }
//    登录
    private void doLogin() {
        name = username.getText().toString();
        pass = password.getText().toString();
        if (TextUtils.isEmpty(name)){
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_LONG).show();
        }
        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_LONG).show();
        }
//        走登录
        mainPersenterlml.doLogin(name, pass);
    }

    @Override
    public void success(String data) {
        UserInBean bean = new Gson().fromJson(data, UserInBean.class);
        sp.edit().putString("username",bean.getMessage())
        .putString("password",bean.getStatus());
        startActivity(new Intent(MainActivity.this,ShowActivity.class));
    }

    @Override
    public void fail(String error) {
        Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_LONG).show();
    }
}
