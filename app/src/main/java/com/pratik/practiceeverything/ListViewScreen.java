package com.pratik.practiceeverything;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewScreen extends AppCompatActivity {
ListView listview;
String[] brosandsis = {"Pankaj", "Vinit", "Pooja", "Prathamesh", "Pratik", "Yash",
        "Lokesh", "Anuj", "Sakshi", "Srushti","Ankita", "Arya"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_screen);

        listview = findViewById(R.id.listview);
        ArrayAdapter list = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, brosandsis);
        listview.setAdapter(list);

        listview.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(ListViewScreen.this,"clicked " + brosandsis[position], Toast.LENGTH_SHORT).show());

    }
}