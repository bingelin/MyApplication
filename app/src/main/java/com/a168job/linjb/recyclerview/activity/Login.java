package com.a168job.linjb.recyclerview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import com.a168job.linjb.recyclerview.AppContext;
import com.a168job.linjb.recyclerview.R;
import com.a168job.linjb.recyclerview.bean.User;
import com.a168job.linjb.recyclerview.help.UIHelp;

/**
 * Created by linjb on 2016/8/8.
 */

public class Login extends Activity {
    private EditText et_username,et_password;
    private final static String  LOGIN_USERTYPE_PERSON = 1+"";
    String username,password;
    public AppContext ac;
    private Handler loginHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                User user = (User) msg.obj;
                ac.saveLoginInfo(user);
                UIHelp.showMain(Login.this);
//                UIHelp.showSecond(Login.this);
            } else if (msg.what == -1) {
                UIHelp.toast(Login.this,(String) msg.obj);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ac = (AppContext) getApplication();
        initView();
    }

    private void initView() {
        et_username = (EditText) findViewById(R.id.login_et_username);
        et_password = (EditText) findViewById(R.id.login_et_password);
    }

    public void login(View view) {
        username = et_username.getText().toString();
        password = et_password.getText().toString();
        if (ac.isAllEmpty(username, password)) {
            UIHelp.toast(this, "账号密码不能为空");
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = Message.obtain(loginHandler);
                    User user = ac.loginVerify(username, password, LOGIN_USERTYPE_PERSON);
                    if (user.isOK()) {
                        message.what = 1;
                        message.obj = user;
                    } else {
                        message.what = -1;
                        message.obj = user.getMessage();
                    }
                    message.sendToTarget();
                }
            }).start();

        }
    }
}
