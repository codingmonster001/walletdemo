package com.wallettest.demo.Model;

import com.wallettest.demo.App;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.Web3jFactory;
import org.web3j.protocol.http.HttpService;

public class Web3JService {
    private static Web3j ourInstance = null;

    public static Web3j getInstance() {
        if(ourInstance == null){
            if(App.GLOBAL_NODE.equals("mainnet"))
                ourInstance = Web3jFactory.build(new HttpService("https://api.myetherwallet.com/eth"));
            else if(App.GLOBAL_NODE.equals("rinkeby"))
                ourInstance = Web3jFactory.build(new HttpService("https://rinkeby.infura.io/v3/ff7cb837065f48009f1e3b2039f50df3"));
        }
        return ourInstance;
    }
    public static void removeInitance(){
        ourInstance = null;
    }

    private Web3JService() {
    }


}
