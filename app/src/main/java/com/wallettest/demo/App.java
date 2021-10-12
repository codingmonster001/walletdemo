package com.wallettest.demo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;


public class App extends Application {
    private static Context instance;
    public static final String CONSTENT_MAINNET = "mainnet";
    public static final String CONSTENT_RINKEBY= "rinkeby";

    public static String GLOBAL_USERNAME = "";
    public static String GLOBAL_NODE = CONSTENT_RINKEBY;


    public static Context getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        MultiDex.install(this) ;

    }
}
