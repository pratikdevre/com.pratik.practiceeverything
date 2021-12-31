package com.pratik.practiceeverything;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();

        ImageView image = findViewById(R.id.imageView);


        Animation animation = AnimationUtils.loadAnimation(this, R.anim.custom_anim);
        image.setAnimation(animation);
        Thread td = new Thread() {
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent it = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        };
        td.start();
    }
}