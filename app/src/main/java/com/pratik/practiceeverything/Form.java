package com.pratik.practiceeverything;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Form extends AppCompatActivity {

    CheckBox cb1, cb2, cb3, cb4;
    RadioGroup gender;
    RadioButton male, female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);

        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        gender = findViewById(R.id.radioGroup);

        gender.setOnCheckedChangeListener((group, checkedId) -> {
            if (male.isChecked()) Toast.makeText(getApplicationContext(), "Your gender is Male", Toast.LENGTH_SHORT).show();
            else if(female.isChecked()) Toast.makeText(getApplicationContext(), "Your gender is Female", Toast.LENGTH_SHORT).show();
            else Toast.makeText(getApplicationContext(), "Your gender is Other", Toast.LENGTH_SHORT).show();
        });

        cb1.setOnClickListener(v -> {
            if (cb1.isChecked()) Toast.makeText(Form.this, "You selected english", Toast.LENGTH_SHORT).show();
            else Toast.makeText(Form.this, "Unchecked english", Toast.LENGTH_SHORT).show();
        });
        cb2.setOnClickListener(v -> {
            if (cb2.isChecked()) Toast.makeText(Form.this, "You selected marathi", Toast.LENGTH_SHORT).show();
            else Toast.makeText(Form.this, "Unchecked marathi", Toast.LENGTH_SHORT).show();
        });
        cb3.setOnClickListener(v -> {
            if (cb3.isChecked()) Toast.makeText(Form.this, "You selected hindi", Toast.LENGTH_SHORT).show();
            else Toast.makeText(Form.this, "Unchecked hindi", Toast.LENGTH_SHORT).show();
        });
        cb4.setOnClickListener(v -> {
            if (cb4.isChecked()) Toast.makeText(Form.this, "You selected urdu", Toast.LENGTH_SHORT).show();
            else Toast.makeText(Form.this, "Unchecked urdu", Toast.LENGTH_SHORT).show();
        });

    }
}