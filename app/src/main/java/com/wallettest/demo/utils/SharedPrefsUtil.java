package com.wallettest.demo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtil {

    public static final String SHARED_APP_CONTEXT_KEY="ShareKey";
    public static void putValue(Activity context, String key,
                                int value) {
        SharedPreferences.Editor sp = getEditor(context);
        sp.putInt(key, value);
        sp.commit();
    }


    public static void putValue(Activity context, String key,
                                boolean value) {
        SharedPreferences.Editor sp = getEditor(context);
        sp.putBoolean(key, value);
        sp.commit();
    }

    public static String putValue(Activity context, String key,
                                  String value) {
        SharedPreferences.Editor sp = getEditor(context);
        sp.putString(key, value);
        sp.commit();
        return value;
    }

    public static void putValue(Activity context, String key,
                                float value) {
        SharedPreferences.Editor sp = getEditor(context);
        sp.putFloat(key, value);
        sp.commit();
    }

    public static void putValue(Activity context, String key,
                                long value) {
        SharedPreferences.Editor sp = getEditor(context);
        sp.putLong(key, value);
        sp.commit();
    }

    public static int getValue(Activity context, String key,
                               int defValue) {
        SharedPreferences sp = getSharedPreferences(context);
        int value = sp.getInt(key, defValue);
        return value;
    }
    public static int getValue(Context context, String key,
                               int defValue) {
        SharedPreferences sp = getSharedPreferences(context);
        int value = sp.getInt(key, defValue);
        return value;
    }

    public static String getValue(Activity context, String key,
                                  String defValue) {
        SharedPreferences sp = getSharedPreferences(context);
        String value = sp.getString(key, defValue);
        return value;
    }


    public static long getValue(Activity context, String key,
                                long defValue) {
        SharedPreferences sp = getSharedPreferences(context);
        long value = sp.getLong(key, defValue);
        return value;
    }

    private static SharedPreferences.Editor getEditor(Activity context) {
        return getSharedPreferences(context).edit();
    }

    private static SharedPreferences getSharedPreferences(Activity context) {
        return context.getSharedPreferences(SHARED_APP_CONTEXT_KEY, Context.MODE_PRIVATE);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_APP_CONTEXT_KEY, Context.MODE_PRIVATE);
    }

}
