package com.wallettest.demo;

import android.content.Intent;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.btn_login)
    public void onLoginClicked() {
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }
    @OnClick(R.id.btn_register)
    public void onRegisterClicked() {
        startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
    }


}
