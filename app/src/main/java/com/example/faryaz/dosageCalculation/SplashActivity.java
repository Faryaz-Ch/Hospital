package com.example.faryaz.anglesea;

/**
 * Created by faryaz on 6/14/2018.
 */

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.faryaz.anglesea.R;

public class SplashActivity  extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private int ProgessStatus = 0;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //Progress Bar and Next Activity Thread
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(ProgessStatus < 100){
                    ProgessStatus++;
                    android.os.SystemClock.sleep(25);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mProgressBar.setProgress(ProgessStatus);
                        }
                    });
                }
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
            }

        }).start();

    }
}
