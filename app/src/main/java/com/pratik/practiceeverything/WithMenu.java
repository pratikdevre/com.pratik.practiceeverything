package com.pratik.practiceeverything;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Objects;

public class WithMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_menu);
        Objects.requireNonNull(getSupportActionBar()).setTitle("With Menu");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.custom_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int myid = item.getItemId();
        if (myid == R.id.item1) Toast.makeText(this, "Item 1 Selected", Toast.LENGTH_SHORT).show();
        else if (myid == R.id.item2) Toast.makeText(this, "Item 2 Selected", Toast.LENGTH_SHORT).show();
        else  Toast.makeText(this, "Item 3 Selected", Toast.LENGTH_SHORT).show();

        return true;
    }
}