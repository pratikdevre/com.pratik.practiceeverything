package com.pratik.practiceeverything;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.counterbutton).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Opening counter screen...", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, CounterScreen.class));
        });

        findViewById(R.id.formbutton).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Opening form screen...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, Form.class));
        });

        findViewById(R.id.senddatabutton).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Data will share from this page to other page", Toast.LENGTH_LONG).show();
            startActivity(new Intent(MainActivity.this, SendDataToOther.class));
        });

        findViewById(R.id.snapchat).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Opening snapchat UI screen", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, SnapChatUI.class));
        });

        findViewById(R.id.listviewpage).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Opening List View", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, ListViewScreen.class));
        });

        findViewById(R.id.sendemail).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Opening Email UI screen", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, SendEmailUI.class));
        });

        findViewById(R.id.camerapage2).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Opening Camera screen...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, OpenCamera.class));
        });

        findViewById(R.id.alarmpage).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Opening Alarm screen...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, AlarmAndTimer.class));
        });

        findViewById(R.id.withmenu).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Opening WithMenu screen...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, WithMenu.class));
        });

        findViewById(R.id.switchonoff).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Opening Light screen...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, SwitchOnOff.class));
        });

        findViewById(R.id.notificationpage).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Opening Notification Sender screen...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, NotificationSendingPage.class));
        });

        findViewById(R.id.databasepage).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Opening Database screen...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, DataBaseConnectionPage.class));
        });

        findViewById(R.id.carproject).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Opening Car Project...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, MainActivityCarProject.class));
        });

        findViewById(R.id.firestoreroject).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Opening Firestore Project...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, UsingFireStore.class));
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_warning)
                .setTitle("Edit").setMessage("Are you sure?")
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNeutralButton("Help", (d, w) -> Toast.makeText(this, "Press 'YES' to Exit", Toast.LENGTH_SHORT).show())
                .setNegativeButton("No", (d, w) -> d.dismiss())
                .show();
    }
}
