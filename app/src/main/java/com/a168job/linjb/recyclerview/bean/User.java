package com.a168job.linjb.recyclerview.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by linjb on 2016/8/8.
 */

public class User {
    private String userName = "";
    private String applyNum = "";
    private String integral = "";
    private String balance  = "";
    private String favoriteNum = "";
    private String interviewNum = "";
    private String resultCode = "";
    private String message = "";
    private String globalId = "";
    private String talentNo = "";
    private String talentDes="";
    private String sessionId = "";
    private String loginEnum = "";

    public String getLoginEnum() {
        return loginEnum;
    }

    public void setLoginEnum(String loginEnum) {
        this.loginEnum = loginEnum;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTalentDes() {
        return talentDes;
    }

    public void setTalentDes(String talentDes) {
        this.talentDes = talentDes;
    }

    public String getTalentNo() {
        return talentNo;
    }

    public void setTalentNo(String talentNo) {
        this.talentNo = talentNo;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getInterviewNum() {
        return interviewNum;
    }

    public void setInterviewNum(String interviewNum) {
        interviewNum = interviewNum;
    }

    public String getFavoriteNum() {
        return favoriteNum;
    }

    public void setFavoriteNum(String favoriteNum) {
        this.favoriteNum = favoriteNum;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(String applyNum) {
        this.applyNum = applyNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static User parse(String jsonData) {
        User u = new User();
        try {
            JSONObject jsonObject = new JSONObject(jsonData).getJSONObject("result");
            u.setUserName(jsonObject.getString("userName"));
            u.setApplyNum(jsonObject.getString("applyNum"));
            u.setBalance(jsonObject.getString("balance"));
            u.setFavoriteNum(jsonObject.getString("favoriteNum"));
            u.setGlobalId(jsonObject.getString("globalId"));
            u.setIntegral(jsonObject.getString("integral"));
            u.setInterviewNum(jsonObject.getString("interviewNum"));
            u.setLoginEnum(jsonObject.getString("loginEnum"));
            u.setMessage(jsonObject.getString("message"));
            u.setResultCode(jsonObject.getString("resultCode"));
            u.setSessionId(jsonObject.getString("sessionId"));
            u.setTalentDes(jsonObject.getString("talentDes"));
            u.setTalentNo(jsonObject.getString("talentNo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return u;
    }

}
