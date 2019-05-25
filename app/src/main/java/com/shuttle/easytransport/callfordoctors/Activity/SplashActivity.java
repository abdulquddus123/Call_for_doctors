package com.shuttle.easytransport.callfordoctors.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.shuttle.easytransport.callfordoctors.MainActivity;
import com.shuttle.easytransport.callfordoctors.R;

public class SplashActivity extends Activity {

    private int SPLASH_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(SPLASH_TIME);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent intent=new Intent(SplashActivity.this,LoginViewActivity.class);
                    startActivity(intent);
//                    if (SharedPref.getPrefForLoginStatus(SplashActivity.this)) {
//
//                        ProjectUtils.genericIntent(SplashActivity.this, HomeActivity.class, null, true);
//                    } else {
//                        ProjectUtils.genericIntent(SplashActivity.this, LoginActivity.class, null, true);
//                    }

                }
            }
        };
        timer.start();
    }
}
