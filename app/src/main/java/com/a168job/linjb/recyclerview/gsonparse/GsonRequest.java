package com.a168job.linjb.recyclerview.gsonparse;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by linjb on 2016/8/8.
 */

public class GsonRequest<T> extends Request<T> {

    Response.Listener<T> mListener;
    Gson mGson;
    Class<T> mClass;

    public GsonRequest(int method, String url,Class<T> clazz,Response.Listener<T>listener,Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mGson = new Gson();
        mClass = clazz;
        mListener = listener;
    }

    public GsonRequest( String url, Class<T> clazz,Response.Listener<T>listener,Response.ErrorListener errorListener) {
        this(Method.GET, url, clazz,listener,errorListener);
    }

    /**
     * 解析网络请求返回的数据
     * @param response
     * @return
     */
    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(jsonString, mClass), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    /**
     * 调用mListener的onResponse接口，传入响应的数据，
     * 这样一来以后谁调用了Listener的onResponse方法就可以接受网络请求回调的数据
     * @param t
     */
    @Override
    protected void deliverResponse(T t) {
        mListener.onResponse(t);
    }
}

