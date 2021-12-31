package com.pratik.practiceeverything;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmAndTimer extends AppCompatActivity {
Button setalarm;
TextView hr, min, msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_and_timer);

        setalarm = findViewById(R.id.setalarm);
        hr = findViewById(R.id.hr);
        min = findViewById(R.id.min);
        msg = findViewById(R.id.msg);

        setalarm.setOnClickListener(v->{
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
            intent.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(hr.getText().toString()));
            intent.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(min.getText().toString()));
            intent.putExtra(AlarmClock.EXTRA_MESSAGE, msg.getText().toString());
            if (intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            }
            else Toast.makeText(this, "No Supported App", Toast.LENGTH_SHORT).show();
        });
    }
}