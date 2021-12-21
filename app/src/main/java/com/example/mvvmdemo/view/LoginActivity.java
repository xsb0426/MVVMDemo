package com.example.mvvmdemo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.databinding.ActivityLoginBinding;
import com.example.mvvmdemo.viewmodel.LoginViewModel;


/**
 * MVVM中  M为业务层 V为视图层 VM相当于MVP中的P,但是VM不持有V的引用
 */
public class LoginActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        //获取viewModel实例
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(LoginViewModel.class);
        binding.setViewModel(viewModel);
        //双向绑定需要加这句，否则不起作用
        binding.setLifecycleOwner(this);

        initListener();
    }

    private void initListener() {

        //相当于网络请求回调，观察回调数据变化
        viewModel.getLoginResponseLD().observe(LoginActivity.this, loginResponse -> {
            boolean loginSuccess = loginResponse.isLoginSuccess();
            if (loginSuccess) {
                Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        binding.btnLogin.setOnClickListener(v -> {
            String loginName = viewModel.getLoginNameLD().getValue();
            String pwd = viewModel.getPwdLD().getValue();

            if (TextUtils.isEmpty(loginName)) {
                Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(pwd)) {
                Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                return;
            }

            //请求登录
            viewModel.login();
        });
    }
}