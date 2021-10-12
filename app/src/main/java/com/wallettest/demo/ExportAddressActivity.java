package com.wallettest.demo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.wallettest.demo.utils.KeyStoreUtils;
import com.wallettest.demo.utils.SharedPrefsUtil;

import org.web3j.crypto.Credentials;

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.OnClick;

public class ExportAddressActivity extends BaseActivity {


    @BindView(R.id.et_privatekey)
    EditText et_privatekey;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_export;
    }

    @Override
    protected void initView() {
        String address = SharedPrefsUtil.getValue(this,"Address:" + App.GLOBAL_USERNAME, "");

        try {
            Credentials credentials = KeyStoreUtils.getCredentials(address.substring(2));
            et_privatekey.setText("0x"+credentials.getEcKeyPair().getPrivateKey().toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Get Wallet Info Error", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.btn_back)
    public void onBackClicked() {
        finish();
    }

    @OnClick(R.id.btn_copy)
    public void onCopyClicked() {
        ClipboardManager clipboard = (ClipboardManager)
                getSystemService(Context.CLIPBOARD_SERVICE);
        CharSequence charSequence = et_privatekey.getText().toString();
        ClipData clip = ClipData.newPlainText("label", charSequence);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Copied Successfully", Toast.LENGTH_SHORT).show();
    }

}
