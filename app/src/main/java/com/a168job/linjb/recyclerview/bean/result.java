package com.a168job.linjb.recyclerview.bean;

/**
 * Created by linjb on 2016/8/9.
 */

public class Result {
    private int resultCode = 0;
    private String message = "";

    public boolean isOK() {
        return 0 == resultCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result [resultCode=" + resultCode + ", message=" + message
                + "]";
    }

//    public static Result parse(String json)  {
//        Result res = new Result();
//
//        JSONObject data = null;
//        try {
//            data = new JSONObject(json).getJSONObject("result");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        res.setResultCode(data.optInt("resultCode"));
//        res.setMessage(data.optString("message"));
//        return res;
//    }

}
