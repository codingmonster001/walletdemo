package com.wallettest.demo;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wallettest.demo.utils.KeyStoreUtils;
import com.wallettest.demo.utils.SharedPrefsUtil;

import org.ethereum.geth.Account;
import org.ethereum.geth.Geth;
import org.ethereum.geth.KeyStore;
import org.web3j.crypto.CipherException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.btn_back)
    public void onBackClicked() {
        finish();
    }

    @OnClick(R.id.btn_login)
    public void onLoginClicked() {
        String username = et_username.getText().toString();
        String pwd = et_password.getText().toString();
        if(username.isEmpty()){
            Toast.makeText(this, "Please Input Username", Toast.LENGTH_SHORT).show();
            return;
        }
        if(pwd.isEmpty()){
            Toast.makeText(this, "Please Input Password", Toast.LENGTH_SHORT).show();
            return;
        }
        String save_pwd = SharedPrefsUtil.getValue(this, "Account:"+username, "");
        if(save_pwd.equals(pwd)){
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
            App.GLOBAL_USERNAME = username;
            finish();
            startActivity(new Intent(LoginActivity.this, WalletActivity.class));
        }else{
            if(save_pwd.equals(""))
                Toast.makeText(this, "Please Register First", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
            App.GLOBAL_USERNAME = "";
        }


    }

}
