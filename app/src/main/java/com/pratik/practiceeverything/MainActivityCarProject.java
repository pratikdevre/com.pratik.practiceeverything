package com.pratik.practiceeverything;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Objects;

public class MainActivityCarProject extends AppCompatActivity {
    RecyclerView rcvcar;
    EditText searchCarText;
    FirebaseRecyclerOptions<NewCarCreate.NewCarCreateModel> options;
    FirebaseDatabase fbdb = FirebaseDatabase.getInstance();
    CarAdapter adapter;

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_car_project);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Car App");

        searchCarText = findViewById(R.id.searchcar);
        rcvcar = findViewById(R.id.rcvcar);
        rcvcar.setItemAnimator(null);
        rcvcar.setHasFixedSize(true);

        loadData();

        rcvcar.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CarAdapter(options, getApplicationContext());
        rcvcar.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.search_car_menu, menu);
       MenuItem item = menu.findItem(R.id.searchcar);
        SearchView searchView = (SearchView) item.getActionView();
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               sarchText(query);
               return false;
           }
           @Override
           public boolean onQueryTextChange(String newText) {
               sarchText(newText);
               return false;
           }
       });
       return super.onCreateOptionsMenu(menu);
    }

    private void sarchText(String querytext) {
        Query query = fbdb.getReference("Cars Info").orderByChild("_carname").startAt(querytext.toLowerCase()).endAt(querytext+"\uf8ff");
        options = new FirebaseRecyclerOptions.Builder<NewCarCreate.NewCarCreateModel>()
                .setQuery(query, NewCarCreate.NewCarCreateModel.class)
                .build();

        adapter = new CarAdapter(options, this);
        adapter.startListening();
        rcvcar.setAdapter(adapter);
    }

    private void loadData() {
        Query query = fbdb.getReference("Cars Info");
        options = new FirebaseRecyclerOptions.Builder<NewCarCreate.NewCarCreateModel>()
                .setQuery(query, NewCarCreate.NewCarCreateModel.class)
                .build();
    }

    public void newCarAdd(View view) {
        startActivity(new Intent(this, NewCarCreate.class));
    }

    @Override
    protected void onStop () {
        super.onStop();
        adapter.stopListening();
    }
}