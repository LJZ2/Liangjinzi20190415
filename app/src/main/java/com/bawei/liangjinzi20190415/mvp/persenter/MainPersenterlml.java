package com.bawei.liangjinzi20190415.mvp.persenter;

import com.bawei.liangjinzi20190415.mvp.model.MainModel;
import com.bawei.liangjinzi20190415.mvp.view.MainView;

/**
 * @Author：梁金子
 * @Date：2019/4/15 9:09
 * @Description：描述信息
 */
public class MainPersenterlml implements MainPersenter, MainModel.CallBackListener {

    private MainModel mainModel;
    private MainView mainView;

    public MainPersenterlml(MainModel mainModel, MainView mainView) {
        this.mainModel = mainModel;
        this.mainView = mainView;
    }

    @Override
    public void doLogin(String phone, String pwd) {
        mainModel.doLogin(phone,pwd,this);
    }

    @Override
    public void doRegister(String phone, String pwd) {
        mainModel.doRegister(phone,pwd,this);
    }

    @Override
    public void success(String data) {
        mainView.success(data);
    }

    @Override
    public void fail(String error) {
        mainView.fail(error);
    }

    public void onstory(){
        if (mainView!=null){
            mainView=null;
        }
        if (mainModel!=null){
            mainModel=null;
        }
    }
}
