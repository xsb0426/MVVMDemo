package com.example.mvvmdemo.viewmodel;

/**
 * @author xushibin
 * @date 12/20/21
 * description：
 */
public class LoginResponse {

    private boolean isLoginSuccess;
    private String message;


    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
