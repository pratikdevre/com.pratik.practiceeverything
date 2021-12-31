package com.pratik.practiceeverything;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DbInfo extends AppCompatActivity {

    EditText surname, age;
    TextView showname, showclass;
    FirebaseDatabase database;
    DatabaseReference reference;
    String dbid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_info);
        showname = findViewById(R.id.shownamedb);
        showclass = findViewById(R.id.showclassdb);
        surname = findViewById(R.id.surnamedb);
        age = findViewById(R.id.agedb);
        database = FirebaseDatabase.getInstance();
        showname.setText(getIntent().getStringExtra("name"));
        showclass.setText(getIntent().getStringExtra("classname"));
        dbid = getIntent().getStringExtra("id");
    }

    public void addtoDB(View view) {
        String name, _age;
        String id;
        name = surname.getText().toString();
        _age = age.getText().toString();

        reference = database.getReference("something");
        id = reference.push().getKey();
        Dbholder dbholder = new Dbholder(id,name ,_age);

        reference.child(dbid).setValue(dbholder);
        Toast.makeText(this, "Data Added Successully", Toast.LENGTH_SHORT).show();

    }

    public static class Dbholder {
        String id2, surname, age;

        public Dbholder() {
        }

        public Dbholder(String id, String name, String age) {
            this.id2 = id;
            this.surname = name;
            this.age = age;
        }

        public String getSurname() {
            return surname;
        }

        public String getAge() {
            return age;
        }

        public String getId2() {
            return id2;
        }
    }

}