package com.a168job.linjb.recyclerview.api;

import android.util.Log;

import com.a168job.linjb.recyclerview.AppContext;
import com.a168job.linjb.recyclerview.bean.result;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * 联网api
 * Created by linjb on 2016/8/8.
 */

public class ApiClient {



    public static void login(AppContext ac, String account, String password, String userType) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("account", account);
        params.put("password", password);
        params.put("userType", userType);
        String url = ac.login_url;
        _post(ac, url, params, null);
    }

    private static String _post(AppContext ac, String url, final Map<String, String> params, Object o) {
        final String jsonData = null;
        RequestQueue mQueue = Volley.newRequestQueue(ac.mContext);
        JsonRequest<result> mRequest = new JsonRequest<result>(Request.Method.POST, url, result.class, new Response.Listener<result>() {
            @Override
            public void onResponse(result result) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Response<result> parseNetworkResponse(NetworkResponse networkResponse) {
                return null;
            }
        };
        mQueue.add(mRequest);
        return null;
    }

}
