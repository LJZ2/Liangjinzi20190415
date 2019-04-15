package com.bawei.liangjinzi20190415.net;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author：梁金子
 * @Date：2019/4/15 9:14
 * @Description：描述信息
 */
public class OkHttpUtils {
    private int HTTP_SUCCESS=1000;
    private int HTTP_FAIL=1001;
//    get
    public OkHttpUtils get(String url){
        doHttp(url,0,null);
        return this;
    }

    public OkHttpUtils post(String url,FormBody.Builder formbody){
        doHttp(url,1,formbody);
        return this;
    }

    private void doHttp(String url, int type, FormBody.Builder formbody) {
        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                return null;
            }
        }).build();
        Request.Builder builder = new Request.Builder();
        if (type==0){
            builder.get();
        }else {
            builder.post(formbody.build());
        }
        builder.url(url);
        Request request = builder.build();
        final Message message = Message.obtain();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                message.what=HTTP_FAIL;
                message.obj=e.getMessage();
                handler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                message.what=HTTP_SUCCESS;
                message.obj=response.body().string();
                handler.sendMessage(message);
            }
        });
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==HTTP_SUCCESS){
                String data= (String) msg.obj;
                httpListener.success(data);
            }
            if (msg.what==HTTP_FAIL){
                String error= (String) msg.obj;
                httpListener.fail(error);
            }

        }
    };
//    传递接口
    public interface HttpListener{
        //    成功
        void success(String data);
        //    失败
        void fail(String error);
    }
    private HttpListener httpListener;

    public void result(HttpListener httpListener) {
        this.httpListener = httpListener;
    }

}
