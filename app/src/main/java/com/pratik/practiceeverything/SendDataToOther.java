package com.pratik.practiceeverything;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
public class SendDataToOther extends AppCompatActivity {
Button submit, sharebtn, call, website, whatsappbtn;
EditText name, number, sharestr, whatsapptext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_to_other);

        name = findViewById(R.id.name);
        number = findViewById(R.id. number);
        submit = findViewById(R.id.submit);

        call = findViewById(R.id.call);

        website = findViewById(R.id.website);

        sharestr = findViewById(R.id.sharestr);
        sharebtn = findViewById(R.id.sharebtn);

        whatsappbtn = findViewById(R.id.whatsappbtn);
        whatsapptext = findViewById(R.id.whatsapptext);

        submit.setOnClickListener(V ->{
            String uname = name.getText().toString();
            String unumber = number.getText().toString();
            Intent intent = new Intent(getApplicationContext(), ReceiveDataFromOne.class);
            intent.putExtra("username", uname);
            intent.putExtra("usernumber", unumber);
            startActivity(intent);
        });

        call.setOnClickListener(v->{
            Uri number = Uri.parse("tel:9860232056");
            Intent intent = new Intent(Intent.ACTION_DIAL, number);
            startActivity(intent);
        });

        website.setOnClickListener(v->{
            Uri link = Uri.parse("https://www.amazon.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, link);
            startActivity(intent);
        });
        sharebtn.setOnClickListener(v->{
            String text = sharestr.getText().toString();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            intent.setType("text/plain");

            Intent chooser = Intent.createChooser(intent, "Share with : ");
            startActivity(chooser);
        });

        whatsappbtn.setOnClickListener(v->{
            String text = whatsapptext.getText().toString();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            intent.setType("text/plain");
            intent.setPackage("com.whatsapp");
            startActivity(intent);
        });
    }
};