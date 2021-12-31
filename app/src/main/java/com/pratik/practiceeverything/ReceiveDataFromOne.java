package com.pratik.practiceeverything;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReceiveDataFromOne extends android.app.Activity {
    String username, usernumber;
    TextView t1, t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_data_from_one);
        Intent receivedata = getIntent();
        username = receivedata.getStringExtra("username");
        usernumber = receivedata.getStringExtra("usernumber");
        t1 = findViewById(R.id.gottext);
        t2 = findViewById(R.id.gotnumber);

        t1.setText(username);
        t2.setText(usernumber);
    }
}