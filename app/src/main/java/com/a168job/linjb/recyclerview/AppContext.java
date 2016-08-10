package com.a168job.linjb.recyclerview;

import android.app.Application;
import android.content.Context;

import com.a168job.linjb.recyclerview.api.ApiClient;
import com.a168job.linjb.recyclerview.bean.User;

/**
 * Created by linjb on 2016/8/8.
 */

public class AppContext extends Application{

    public static AppContext instance;

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

    private final static String CONF_SESSIONID = "sessionId";
    private final static String CONF_TALENTNO = "talentNo";
    private final static String CONF_TALENTDES = "talentDes";
    private final static String CONF_GLOBELID = "globalId";
    private final static String CONF_USERNAME = "username";

    public String login_url = "http://app.job168.com:8080" + "/android/person/login";

    public User loginVerify(String account, String password, String userType) {

        return  ApiClient.login(this, account, password, userType);

    }

    public String getFavorites(int pageNo) {
        return ApiClient.getFavorites(this, pageNo);
    }

    /**
     * 创建APPContext单例
     * @param
     * @return
     */
    public static AppContext newInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initUserInfo();
    }

    private void initUserInfo() {
        sessionId = getProperty(CONF_SESSIONID);
        globalId = getProperty(CONF_GLOBELID);
        talentNo = getProperty(CONF_TALENTNO);
        talentDes = getProperty(CONF_TALENTDES);
        username = getProperty(CONF_USERNAME);
    }

    public  void saveLoginInfo(User user) {
        username = user.getUserName();
        applyNum = user.getApplyNum();
        favoriteNum = user.getFavoriteNum();
        InterviewNum = user.getInterviewNum();
        balance = user.getBalance();
        CoinNum = user.getIntegral();

        globalId = user.getGlobalId();
        talentNo = user.getTalentNo();
        talentDes = user.getTalentDes();
        sessionId = user.getSessionId();

        setProperty(CONF_SESSIONID, sessionId);
        setProperty(CONF_GLOBELID, globalId);
        setProperty(CONF_TALENTNO, talentNo);
        setProperty(CONF_TALENTDES, talentDes);
        setProperty(CONF_USERNAME, username);


    }

    private void setProperty(String key, String value) {
        AppConfig.newInstance(this).set(key, value);
    }

    public String getProperty(String key) {
        return AppConfig.newInstance(this).get(key);
    }

    public static boolean isEmpty(String s) {
        if (s.equals("") || s == null) {
            return true;
        }
        return false;
    }

    public static boolean isAllEmpty(String s1, String s2) {
        if (isEmpty(s1) || isEmpty(s2)) {
            return true;
        }
        return false;
    }

    public String getGlobalId() {
        return globalId;
    }
    public String getSessionId() {
        return sessionId;
    }
    public String getTalentNo() {
        return talentNo;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setTalentNo(String talentNo) {
        this.talentNo = talentNo;
    }
}
