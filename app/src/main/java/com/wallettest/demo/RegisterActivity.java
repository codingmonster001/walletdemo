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

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_repassword)
    EditText et_repassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.btn_register)
    public void onRegisterClicked() {
        String username = et_username.getText().toString();
        if(username.isEmpty())
        {
            Toast.makeText(this, "Please Input Username", Toast.LENGTH_SHORT).show();
            return;
        }
        String ppp = SharedPrefsUtil.getValue(this, "Account:" + username, "");
        if(!ppp.isEmpty()){
            Toast.makeText(this, "Already Registered", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = et_password.getText().toString();
        if(password.isEmpty())
        {
            Toast.makeText(this, "Please Input Password", Toast.LENGTH_SHORT).show();
            return;
        }
        String repassword = et_repassword.getText().toString();
        if(!password.equals(repassword))
        {
            Toast.makeText(this, "Confirm Password Is Wrong", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPrefsUtil.putValue(this, "Account:" + username, password);
        Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();
        App.GLOBAL_USERNAME = username;
        finish();
        startActivity(new Intent(RegisterActivity.this, WalletActivity.class));
    }

    @OnClick(R.id.btn_back)
    public void onBackClicked() {
        finish();
    }

}
