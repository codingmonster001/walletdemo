package com.wallettest.demo;

import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallettest.demo.utils.KeyStoreUtils;
import com.wallettest.demo.utils.SharedPrefsUtil;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
import org.web3j.utils.Numeric;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class GenerateWallet extends BaseActivity {
    @BindView(R.id.btn_generate)
    Button btnGenerate;
    @BindView(R.id.tv_mgs)
    TextView tvMgs;
    @BindView(R.id.et_password)
    EditText etPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_generate_wallet;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.btn_generate)
    public void onGenerateClicked() {

        genWallet();
    }
    @OnClick(R.id.btn_back)
    public void onBackClicked() {
        finish();
    }
    private void genWallet() {
        if (etPassword.length() == 0) {
            etPassword.setError("Please Input password");
            return;
        }
        String save_pwd = SharedPrefsUtil.getValue(this, "Account:" + App.GLOBAL_USERNAME, "");
        String password = etPassword.getText().toString();

        if(!save_pwd.equals(password)){
            Toast.makeText(context, "Incorrect Password, Please Input Login Password!", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            File fileDir = new File(Environment.getExternalStorageDirectory().getPath() + "/Keystore");
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            ECKeyPair ecKeyPair = Keys.createEcKeyPair();

            String filename = WalletUtils.generateWalletFile(password, ecKeyPair, fileDir, false);

            KeyStoreUtils.genKeyStore2Files(ecKeyPair);

            String msg = "fileName:\n" + filename
                    + "\nprivateKey:\n" + Numeric.encodeQuantity(ecKeyPair.getPrivateKey())
                    + "\nPublicKey:\n" + Numeric.encodeQuantity(ecKeyPair.getPublicKey());
            tvMgs.setText(msg);
            String address = "0x"+filename.substring(filename.lastIndexOf("--") + 2, filename.length()-5);
            SharedPrefsUtil.putValue(this,"Address:" + App.GLOBAL_USERNAME, address);
            Toast.makeText(this, "Generated Successfully", Toast.LENGTH_SHORT).show();
            btnGenerate.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
