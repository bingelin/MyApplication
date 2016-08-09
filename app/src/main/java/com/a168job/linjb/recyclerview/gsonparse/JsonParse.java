package com.a168job.linjb.recyclerview.gsonparse;

import com.a168job.linjb.recyclerview.bean.Result;
import com.google.gson.Gson;

/**
 * Created by linjb on 2016/8/9.
 */

public class JsonParse {
    public static Result object = new Result();
    public static Result parase(String jsonData) {
        Gson gson = new Gson();
        object = gson.fromJson(jsonData, Result.class);
        return object;
    }
}
