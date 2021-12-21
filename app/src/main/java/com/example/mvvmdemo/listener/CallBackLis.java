package com.example.mvvmdemo.listener;

/**
 * @author xushibin
 * @date 12/20/21
 * description：
 */
public interface CallBackLis {

    void success(String backStr);

    void failure(int errorCode, String error);

}
