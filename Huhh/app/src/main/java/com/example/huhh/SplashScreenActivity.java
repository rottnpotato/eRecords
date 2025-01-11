package com.example.huhh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if(pref.getBoolean("first_open",true)) {
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    finish();
                }
            });thread.start();

        }else{
            Thread thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    startActivity(new Intent(SplashScreenActivity.this, DashActivity.class));
                    finish();
                }
            });thread.start();

        }
    }
}