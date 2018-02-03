package com.weisj.pj.base.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.weisj.pj.R;

public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                //execute the task
                finish();
                startActivity(new Intent(StartActivity.this, LoginActivity.class));
            }
        }, 2000);

    }
}
