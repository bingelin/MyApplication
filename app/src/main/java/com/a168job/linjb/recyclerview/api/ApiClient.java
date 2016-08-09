package com.a168job.linjb.recyclerview.api;

import android.util.Log;

import com.a168job.linjb.recyclerview.AppContext;
import com.a168job.linjb.recyclerview.bean.User;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * 联网api
 * Created by linjb on 2016/8/8.
 */

public class ApiClient {
    private static String jsonData="";


    public static User login(AppContext ac, String account, String password, String userType) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("account", account);
        params.put("password", password);
        params.put("userType", userType);
        String url = ac.login_url;
        return User.parse(_post(ac, url, params, null));
    }

    private static String _post(AppContext ac, final String url, final Map<String, String> params, Object o) {

        RequestQueue mQueue = Volley.newRequestQueue(ac.getApplicationContext());
        StringRequest mQuest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.i("Binge", s);
                jsonData = s;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

//
//        GsonRequest<Result<User>> mQuest = new GsonRequest<Result<User>>(Request.Method.POST, url, Result<User>.getClass(), new Response.Listener<Result<User>>() {
//            @Override
//            public void onResponse(Result<User> userResult) {
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return params;
//            }
//        };
        mQueue.add(mQuest);

        return jsonData;
    }

}
