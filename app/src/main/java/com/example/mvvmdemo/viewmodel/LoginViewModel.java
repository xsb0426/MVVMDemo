package com.example.mvvmdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmdemo.listener.CallBackLis;
import com.example.mvvmdemo.model.HttpRequest;

/**
 * @author xushibin
 * @date 12/20/21
 * description：
 */
public class LoginViewModel extends AndroidViewModel {

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<String> loginNameLD = new MutableLiveData<>();
    private MutableLiveData<String> pwdLD = new MutableLiveData<>();
    private MutableLiveData<Boolean> loadingLD = new MutableLiveData<>();

    private MutableLiveData<LoginResponse> loginResponseLD = new MutableLiveData<>();

    public MutableLiveData<String> getLoginNameLD() {
        return loginNameLD;
    }

    public void setLoginNameLD(MutableLiveData<String> loginNameLD) {
        this.loginNameLD = loginNameLD;
    }

    public MutableLiveData<String> getPwdLD() {
        return pwdLD;
    }

    public void setPwdLD(MutableLiveData<String> pwdLD) {
        this.pwdLD = pwdLD;
    }

    public MutableLiveData<Boolean> getLoadingLD() {
        return loadingLD;
    }

    public void setLoadingLD(MutableLiveData<Boolean> loadingLD) {
        this.loadingLD = loadingLD;
    }

    public MutableLiveData<LoginResponse> getLoginResponseLD() {
        return loginResponseLD;
    }

    public void setLoginResponseLD(MutableLiveData<LoginResponse> loginResponseLD) {
        this.loginResponseLD = loginResponseLD;
    }

    public void login() {
        loadingLD.setValue(true);
        HttpRequest.login(loginNameLD.getValue(), pwdLD.getValue(), new CallBackLis() {
            @Override
            public void success(String backStr) {
                loadingLD.setValue(false);
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setLoginSuccess(true);
                loginResponse.setMessage("登录成功！");
                loginResponseLD.setValue(loginResponse);
            }

            @Override
            public void failure(int errorCode, String error) {
                loadingLD.setValue(false);
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setLoginSuccess(false);
                loginResponse.setMessage(error);
                loginResponseLD.setValue(loginResponse);
            }
        });
    }
}
