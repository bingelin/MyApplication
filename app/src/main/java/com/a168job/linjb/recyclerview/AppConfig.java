package com.a168job.linjb.recyclerview;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 配置管理类
 * Created by linjb on 2016/8/9.
 */

public class AppConfig {

    private final static String APP_CONFIG = "config_new";

    private static AppConfig instance;
    private Context context;

    public AppConfig() {

    }

    public static AppConfig newInstance(Context context) {

        if (instance == null) {
            instance = new AppConfig();
            instance.context = context;
        }
        return instance;
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE);
    }

    public void set(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String get(String key) {

        return getSharedPreferences(context).getString(key, "");

    }
}
