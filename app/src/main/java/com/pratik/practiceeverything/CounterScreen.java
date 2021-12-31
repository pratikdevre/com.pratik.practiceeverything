package com.pratik.practiceeverything;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CounterScreen extends AppCompatActivity {

    Button addbtn, resetbtn;
    TextView countshow;
    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_screen);

        addbtn= (Button) findViewById(R.id.addbtn);
        resetbtn = (Button) findViewById(R.id.resetbtn);
        countshow = (TextView) findViewById(R.id.countshow);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countshow.setText("Counter is "+ ++count);
            }
        });
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                countshow.setText("Counter is "+ count);
            }
        });
    }
}