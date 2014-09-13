package com.kolumbus.jugendhackt.kolumbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;


public class Splash extends Activity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHandler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(Splash.this, Input.class);
                startActivity(intent);
            }
        }, 2500);


    }



}
