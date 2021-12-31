package com.pratik.practiceeverything;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.widget.ImageView;

public class SwitchOnOff extends AppCompatActivity {

    SwitchCompat switch1;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_on_off);
        switch1 = findViewById(R.id.switch1);
        image = findViewById(R.id.bulbimage);
        switch1.setOnClickListener(v->{
            if (switch1.isChecked()){
                image.setImageResource(R.drawable.bulb2);
            }
            else image.setImageResource(R.drawable.bulb1);

        });
    }
}