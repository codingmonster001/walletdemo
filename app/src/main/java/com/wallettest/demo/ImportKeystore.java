package com.wallettest.demo;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wallettest.demo.utils.KeyStoreUtils;

import org.ethereum.geth.Account;
import org.ethereum.geth.Geth;
import org.ethereum.geth.KeyStore;
import org.web3j.crypto.CipherException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import butterknife.BindView;
import butterknife.OnClick;


public class ImportKeystore extends BaseActivity {


    @BindView(R.id.et_keystore)
    EditText etKeystore;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_import)
    Button btnImport;
    @BindView(R.id.tv_mgs)
    TextView tvMgs;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_import_keystore;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.btn_import)
    public void onViewClicked() {

        importKeyStore();
    }


    private void importKeyStore() {
        if (etKeystore.length() == 0) {
            etKeystore.setError("Please Input Keystore");
            return ;
        }
        if (etPassword.length() == 0) {
            etPassword.setError("Pleasepassword");
            return;
        }
        File file = new File(getFilesDir(), "keystore/");
        KeyStore keyStore = new KeyStore(file.getAbsolutePath(), Geth.LightScryptN, Geth.LightScryptP  );
        String pwd = etPassword.getText().toString();
        String keystore = etKeystore.getText().toString();
        try {

            Account account = keyStore.importKey(keystore.getBytes(Charset.forName("UTF-8")), pwd, KeyStoreUtils.DEFAULTKEY);

            Log.e("keystore", "url: " +account.getURL()+"address"+account.getAddress());

//            ObjectMapper mapper = new ObjectMapper();
//
//            WalletFile walletFile = mapper.readValue(keystore, WalletFile.class);
//
//            Credentials credentials = Credentials.create(Wallet.decrypt(pwd, walletFile));
//            ECKeyPair ecKeyPair = credentials.getEcKeyPair();
//            KeyStoreUtils.genKeyStore2Files(ecKeyPair);
//
//            String msg = "address:\n" + account.getAddress()
//                    + "\nprivateKey:\n" + Numeric.encodeQuantity(ecKeyPair.getPrivateKey())
//                    + "\nPublicKey:\n" + Numeric.encodeQuantity(ecKeyPair.getPublicKey());

            tvMgs.setText("url: " +account.getURL()+"address"+account.getAddress());


//            Log.e("importKey", msg);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }


    }




}
