package com.a168job.linjb.recyclerview.help;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.a168job.linjb.recyclerview.activity.Main;

/**
 * Created by linjb on 2016/8/9.
 */

public class UIHelp {

    public static void toast(Context c,String s) {
        Toast.makeText(c, s, Toast.LENGTH_SHORT).show();
    }

    public static void showMain(Activity activity) {
        Intent intent = new Intent(activity, Main.class);
        activity.startActivity(intent);
    }

}
