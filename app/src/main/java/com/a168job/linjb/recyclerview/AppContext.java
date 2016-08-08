package com.a168job.linjb.recyclerview;

import android.content.Context;

import com.a168job.linjb.recyclerview.api.ApiClient;

/**
 * Created by linjb on 2016/8/8.
 */

public class AppContext {

    public static AppContext instance = null;

    public static Context mContext = null;

    /**
     * 是否处于登录状态
     */
    private boolean isLogin = false;

    /**
     * 全局用户编号
     */
    public String globalId = "";
    /**
     * 会话ID
     */
    private String sessionId = "";
    /**
     * 人才编号
     */
    private String talentNo = "";
    /**
     * 人才验码
     */
    private String talentDes = "";

    /**
     * 人才名称
     */
    public String username = "";

    /**
     * 当前职位申请个数
     */
    public String applyNum = "0";
    /**
     * 当前职位收藏个数
     */
    public String favoriteNum = "0";
    /**
     * 当前未读面试通知个数
     */
    public String InterviewNum = "0";
    /**
     * 当前职币个数
     */
    public String CoinNum = "0";
    /**
     * 当前余额数
     */
    public String balance = "0";

    /**
     * 当前已创建简历个数
     */
    public int currentResumeNum = 0;

    public String login_url = "http://10.88.1.38:8088" + "/android/person/login";

    public void loginVerify(String account, String password,String userType) {

        ApiClient.login(this, account, password, userType);
    }

    private AppContext() {

    }

    public static AppContext newInstance(Context context) {
        mContext = context;
        if (null == instance) {
            instance = new AppContext();
        }
        return instance;
    }
}
