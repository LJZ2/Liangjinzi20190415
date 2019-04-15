package com.bawei.liangjinzi20190415.mvp.model;

import okhttp3.FormBody;

/**
 * @Author：梁金子
 * @Date：2019/4/15 9:01
 * @Description：描述信息
 */
public class MainModellml implements MainModel {
//    登录
    @Override
    public void doLogin(String phone, String pwd, CallBackListener callBackListener) {

    }
//    注册
    @Override
    public void doRegister(String phone, String pwd, CallBackListener callBackListener) {
        String url="http://172.17.8.100/small/user/v1/register";
        FormBody builder=new FormBody.Builder().build();

    }
}
