package com.a168job.linjb.recyclerview.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by linjb on 2016/8/10.
 */

public class JobFavorite extends Result{

    private String favoriteNo = "";
    private String unitNo = "";
    private String name = "";
    private String recruitNo = "";
    private String station = "";
    private String stowdate = "";
    private int expri;
    private String salary1="";
    private String salary2="";
    private String workLoc1st = "";
    private String workLoc2nd = "";
    private String workLoc3rd = "";
    private int state;
    private String regDate = "";
    private String expDate = "";



    public boolean isExpri() {
        return expri == 0;
    }

    public boolean isPause() {
        return state == 1;
    }

    public String getFavoriteNo() {
        return favoriteNo;
    }

    public void setFavoriteNo(String favoriteNo) {
        this.favoriteNo = favoriteNo;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecruitNo() {
        return recruitNo;
    }

    public void setRecruitNo(String recruitNo) {
        this.recruitNo = recruitNo;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getStowdate() {
        return stowdate;
    }

    public void setStowdate(String stowdate) {
        this.stowdate = stowdate;
    }

    public int getExpri() {
        return expri;
    }

    public void setExpri(int expri) {
        this.expri = expri;
    }

    public String getSalary1() {
        return salary1;
    }

    public void setSalary1(String salary1) {
        this.salary1 = salary1;
    }

    public String getSalary2() {
        return salary2;
    }

    public void setSalary2(String salary2) {
        this.salary2 = salary2;
    }

    public String getWorkLoc1st() {
        return workLoc1st;
    }

    public void setWorkLoc1st(String workLoc1st) {
        this.workLoc1st = workLoc1st;
    }

    public String getWorkLoc2nd() {
        return workLoc2nd;
    }

    public void setWorkLoc2nd(String workLoc2nd) {
        this.workLoc2nd = workLoc2nd;
    }

    public String getWorkLoc3rd() {
        return workLoc3rd;
    }

    public void setWorkLoc3rd(String workLoc3rd) {
        this.workLoc3rd = workLoc3rd;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public static ArrayList<JobFavorite> parse(String jsonData) {
        ArrayList<JobFavorite> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData).getJSONObject("result");
            JSONArray page = jsonObject.optJSONArray("pFavoritePage");
            if (page != null) {
                for (int i = 0; i < page.length(); i++) {
                    JSONObject o = page.getJSONObject(i);
                    String favoriteNo = o.optString("favoriteNo");
                    String name = o.optString("name");
                    String recruitNo = o.optString("recruitNo");
                    String unitNo = o.optString("unitNo");
                    String station = o.optString("station");
                    String stowdate = o.optString("stowdate");
                    int expri = o.optInt("expri");
                    String salary1 = o.optString("salary1");
                    String salary2 = o.optString("salary2");
                    String workLoc1st = o.optString("workLoc1st");
                    String workLoc2nd = o.optString("workLoc2nd");
                    String workLoc3rd = o.optString("workLoc3rd");
                    int state = o.optInt("state");
                    String regDate = o.optString("regDate");
                    String expDate = o.optString("expDate");
                    int resultCode = o.optInt("resultCode");
                    String message = o.optString("message");

                    JobFavorite a = new JobFavorite();
                    a.setFavoriteNo(favoriteNo);
                    a.setName(name);
                    a.setRecruitNo(recruitNo);
                    a.setUnitNo(unitNo);
                    a.setStation(station);
                    a.setStowdate(stowdate);
                    a.setExpri(expri);
                    a.setSalary1(salary1);
                    a.setSalary2(salary2);
                    a.setWorkLoc1st(workLoc1st);
                    a.setWorkLoc2nd(workLoc2nd);
                    a.setWorkLoc3rd(workLoc3rd);
                    a.setState(state);
                    a.setRegDate(regDate);
                    a.setExpDate(expDate);
                    a.setResultCode(resultCode);
                    a.setMessage(message);
                    list.add(a);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
