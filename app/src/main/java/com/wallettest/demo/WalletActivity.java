package com.wallettest.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wallettest.demo.Model.Web3JService;
import com.wallettest.demo.utils.KeyStoreUtils;
import com.wallettest.demo.utils.SharedPrefsUtil;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WalletActivity extends BaseActivity {

    private static final int REQUEST_NET = 100;
    @BindView(R.id.txt_address)
    TextView txt_address;

    @BindView(R.id.txt_eth)
    TextView txt_eth;
    @BindView(R.id.txt_net)
    TextView txt_net;

    @BindView(R.id.btn_create)
    Button btn_create;
    @BindView(R.id.btn_importprivate)
    Button btn_import;
//    @BindView(R.id.btn_export)
//    Button btn_export;
    @BindView(R.id.btn_send)
    Button btn_send;

    private String address;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_wallet;
    }


    @Override
    protected void initView() {
        context = this;

        address = SharedPrefsUtil.getValue(this,"Address:" + App.GLOBAL_USERNAME, "");
        if(address.isEmpty()){
            txt_address.setText("You didn't create wallet yet");
            btn_send.setVisibility(View.GONE);

            return;
        }else
        {
            txt_address.setText(address);
            btn_send.setVisibility(View.VISIBLE);

            btn_create.setVisibility(View.GONE);
            btn_import.setVisibility(View.GONE);
        }
        txt_net.setText(App.GLOBAL_NODE);

        initProgress();
        Observable
                .create((ObservableOnSubscribe<EthGetBalance>) e -> {
                    Web3j web3j = Web3JService.getInstance();

                    EthGetBalance  balance= web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST ).send();
                    e.onNext(balance);
                    e.onComplete();

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(respon -> {
                    String result = respon.getResult();
                    java.math.BigDecimal tokenValue = Convert.fromWei(Numeric.toBigInt(result).toString(), Convert.Unit.ETHER);
                    txt_eth.setText(tokenValue.toString());
                    Log.e("sinboss", result);
                    hideProgress();
                }, throwable -> {
                    throwable.printStackTrace();
                    hideProgress();
                });
    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.btn_logout)
    public void onLogoutClicked()  {
        finish();
        App.GLOBAL_USERNAME = "";
    }
    @OnClick(R.id.btn_create)
    public void onCreateClicked()  {
        startActivity(new Intent(this, GenerateWallet.class));
    }
    @OnClick(R.id.btn_importprivate)
    public void importPrivateKey()  {
        startActivity(new Intent(this, ImportPrivateKey.class));
    }
//    @OnClick(R.id.btn_importkeystore)
//    public void importKeyStore()  {
//        startActivity(new Intent(this, ImportPrivateKey.class));
//    }
//    @OnClick(R.id.btn_export)
//    public void exportPrivateKey()  {
//        startActivity(new Intent(this, ExportAddressActivity.class));
//    }
    @OnClick(R.id.btn_send)
    public void onClickSend()  {
        startActivity(new Intent(this, SendActivity.class));
    }

    @OnClick(R.id.btn_switch)
    public void onSwitchClick()  {
        startActivityForResult(new Intent(this, SwitchNetworkActivity.class), REQUEST_NET);
    }
    @OnClick(R.id.btn_refresh)
    public void onRefreshClick()  {
        initView();
    }

    @Override
    protected void onResume() {
        initView();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_NET){
            if(resultCode == RESULT_OK){
                String net = data.getStringExtra("net");
                if(!net.equals(App.GLOBAL_NODE)){
                    App.GLOBAL_NODE = net;
                    Web3JService.removeInitance();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
