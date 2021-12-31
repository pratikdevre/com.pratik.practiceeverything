package com.pratik.practiceeverything;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendEmailUI extends AppCompatActivity {
    EditText emailto, subject, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email_ui);
        emailto = findViewById(R.id.emailto);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        Button send = findViewById(R.id.send);
        send.setOnClickListener(v->{
            sendEmail();
        });
    }
    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        String[] emails = emailto.getText().toString().split(",");
        intent.putExtra(Intent.EXTRA_EMAIL, emails);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Send Email To:"));
    }
}