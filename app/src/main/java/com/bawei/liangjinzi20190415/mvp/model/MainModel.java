package com.bawei.liangjinzi20190415.mvp.model;

/**
 * @Author：梁金子
 * @Date：2019/4/15 8:58
 * @Description：描述信息
 */
public interface MainModel {

    interface CallBackListener{
        //    成功
        void success(String data);
        //    失败
        void fail(String error);
    }
//    登录
    void doLogin(String phone,String pwd,CallBackListener callBackListener);
//    注册
    void doRegister(String phone,String pwd,CallBackListener callBackListener);
}
