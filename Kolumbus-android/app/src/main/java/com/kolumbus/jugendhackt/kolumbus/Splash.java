package com.kolumbus.jugendhackt.kolumbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class Splash extends Activity {

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       final TextView tV_Splash = (TextView)findViewById(R.id.tV_Splash);

        mHandler.postDelayed(new Runnable() {
            public void run() {
                tV_Splash.setText("Hallo!");
                mHandler.postDelayed(new Runnable() {
                    public void run() {
                        tV_Splash.setText("Ich bin Kolumbus");
                        mHandler.postDelayed(new Runnable() {
                            public void run() {
                                tV_Splash.setText("Lass mich deine Reise Planen");
                                mHandler.postDelayed(new Runnable() {
                                    public void run() {
                                        Intent intent = new Intent(Splash.this, Input.class);
                                        startActivity(intent);
                                    }
                                }, 1000);
                            }
                        }, 1000);
                    }
                }, 1000);
            }
        }, 1000);








    }



}
