package com.wallettest.demo;

import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import com.wallettest.demo.utils.SharedPrefsUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class SwitchNetworkActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_switchnetwork;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.btn_mainnet)
    public void onMainnetClicked() {
        Intent res = new Intent();
        res.putExtra("net", App.CONSTENT_MAINNET);
        setResult(RESULT_OK, res);
        finish();
    }

    @OnClick(R.id.btn_rinkeby)
    public void onRinkebyClicked() {
        Intent res = new Intent();
        res.putExtra("net", App.CONSTENT_RINKEBY);
        setResult(RESULT_OK, res);
        finish();
    }

}
