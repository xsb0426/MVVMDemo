package com.example.mvvmdemo.model;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.example.mvvmdemo.listener.CallBackLis;

/**
 * @author xushibin
 * @date 12/20/21
 * description：
 */
public class HttpRequest {

    /**
     * 模拟网络请求操作 example/123456登录成功
     *
     * @param loginName
     * @param pwd
     * @param callBackLis
     */
    public static void login(String loginName, String pwd, CallBackLis callBackLis) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {

                        if (TextUtils.equals(loginName, "example") && TextUtils.equals(pwd, "123456")) {
                            callBackLis.success("登录成功");
                        } else {
                            callBackLis.failure(101, "登录失败");
                        }
                    }
                });
            }
        }).start();

    }

}
