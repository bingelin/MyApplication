package com.a168job.linjb.recyclerview.api;

import android.app.Application;
import android.util.Log;

import com.a168job.linjb.recyclerview.AppContext;
import com.a168job.linjb.recyclerview.bean.JobFavorite;
import com.a168job.linjb.recyclerview.bean.Urls;
import com.a168job.linjb.recyclerview.bean.User;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 联网api
 * Created by linjb on 2016/8/8.
 */

public class ApiClient extends Application{


    public static User login(AppContext ac, String account, String password, String userType) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("password", password);
        params.put("account", account);
        params.put("userType", userType);
        String url = ac.login_url;
//        String resq = _post(ac, url, params, null);
        return User.parse(_post(ac, url, params, null));
    }

    public static ArrayList<JobFavorite> getFavorites(AppContext ac, int pageNo) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("globalId", ac.getGlobalId());
        params.put("sessionId", ac.getSessionId());
        params.put("talentNo", ac.getTalentNo());
        String url = Urls.JOB_FAVORITE_URL;
        return JobFavorite.parse(_post(ac, url, params, null));

    }

    private static String _post(AppContext ac, final String Url, final Map<String, String> params, Object o) {
//        try {
//            URL url = new URL(Url);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
////            Set<String> keySet = params.keySet();
////            for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
////                String key = iterator.next();
////                String value = params.get(key);
////                Log.i("Binge",key+"++++"+value);
////                connection.setRequestProperty(key, value);
////            }
//            connection.setRequestProperty("account", "bingelin");
//            connection.setRequestProperty("password", params.get("password"));
//            connection.setRequestProperty("userType", params.get("userType"));
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.setConnectTimeout(1000 * 5);
//            InputStream is = connection.getInputStream();
//            System.out.println("available---"+is.available());
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            int len;
//            while ((len = is.read())!= -1) {
//                baos.write(len);
//            }
//            jsonData = baos.toString();
//        } catch (java.io.IOException e) {
//            e.printStackTrace();
//        }
//        Log.i("Binge", jsonData);
//        final String[] jsonData = {""};
//        StringRequest mQuest = new StringRequest(com.android.volley.Request.Method.POST, Url, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String s) {
//                Log.i("Binge", "------"+s);
//                jsonData[0] = s;
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return params;
//            }
//        };
//        ac.getRequestQueue().add(mQuest);
        String jsonData = "";
        FormEncodingBuilder builder = new FormEncodingBuilder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                Log.i("参数:", entry.getKey() + ":" + entry.getValue());
                builder.add(entry.getKey(), entry.getValue().toString());
            }
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(Url).post(builder.build()).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                jsonData =  response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("jsonData---"+jsonData);
        return jsonData;
//
//        return jsonData[0];
    }

}
