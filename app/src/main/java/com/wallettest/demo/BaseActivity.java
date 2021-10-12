package com.wallettest.demo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    public static BaseActivity context;     //Activity context
    public Dialog progressDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
    }
    protected abstract  int getLayoutId();
    protected abstract void initView();
    protected abstract void initData();

    public void initProgress() {
        if(progressDialog == null){
            progressDialog = new Dialog(context, R.style.progress_dialog);
            progressDialog.setContentView(R.layout.waitdialog);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            TextView progressDialogmsg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
            progressDialogmsg.setText("Please Wait...");
        }
        try{
            progressDialog.show();
        }catch (Exception e) {

        }
    }
    public void hideProgress()
    {
        try {
            progressDialog.dismiss();
        }catch (Exception e){

        }
    }


}
