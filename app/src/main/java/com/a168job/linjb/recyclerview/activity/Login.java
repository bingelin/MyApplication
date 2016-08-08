package com.a168job.linjb.recyclerview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.a168job.linjb.recyclerview.AppContext;
import com.a168job.linjb.recyclerview.R;

/**
 * Created by linjb on 2016/8/8.
 */

public class Login extends Activity {
    private EditText et_username,et_password;
    private final static String  LOGIN_USERTYPE_PERSON = 1+"";
    public AppContext ac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ac = AppContext.newInstance(this);
        initView();
    }

    private void initView() {
        et_username = (EditText) findViewById(R.id.login_et_username);
        et_password = (EditText) findViewById(R.id.login_et_password);
    }

    public void login(View view) {
        String username,password;
        username = et_username.getText().toString();
        password = et_password.getText().toString();
        Log.i("loginMessage", username + " " + password);
        ac.loginVerify(username, password, LOGIN_USERTYPE_PERSON);
    }

}
