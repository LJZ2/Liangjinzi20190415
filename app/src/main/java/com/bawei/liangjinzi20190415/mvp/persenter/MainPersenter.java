package com.bawei.liangjinzi20190415.mvp.persenter;

import com.bawei.liangjinzi20190415.mvp.model.MainModel;

/**
 * @Author：梁金子
 * @Date：2019/4/15 9:01
 * @Description：描述信息
 */
public interface MainPersenter {
    //    登录
    void doLogin(String phone,String pwd);
    //    注册
    void doRegister(String phone,String pwd);
}
