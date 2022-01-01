package com.pratik.practiceeverything.UsingFirestoreProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pratik.practiceeverything.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsingFireStore extends AppCompatActivity {
    Button addbtnfs, fetchbtnfs;
    EditText namefs, emailfs;
    FirebaseFirestore fbfs;
    RecyclerView rcvfetchedfs;
    DataAdapterFS adapter;
    ArrayList<DataModelFS> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_fire_store);
        addbtnfs = findViewById(R.id.addbtnfs);
        rcvfetchedfs = findViewById(R.id.rcvfetchedfs);
        namefs = findViewById(R.id.namefs);
        emailfs = findViewById(R.id.emailfs);

        fbfs = FirebaseFirestore.getInstance();

        addbtnfs.setOnClickListener(v -> {
            insertData();
        });
        fetchData();

        adapter = new DataAdapterFS(list);
        rcvfetchedfs.setAdapter(adapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void fetchData() {

        Toast.makeText(this, "fetching", Toast.LENGTH_SHORT).show();
        fbfs.collection("Person Data").get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<DocumentSnapshot> list2 = queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot q : list2) {
                        list.add(q.toObject(DataModelFS.class));
                        Log.d("sfdf", "onSuccess: " + q.toObject(DataModelFS.class));
                        Log.d("sfdf", "onSuccess: " + q.getString("Email"));
                        Log.d("sfdf", "onSuccess: " + q.getString("Name"));
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show());


        }

//    method to insert to data into the firestore
    private void insertData() {
        Map<String, String> pDetail = new HashMap<>();
        pDetail.put("Name", namefs.getText().toString().trim());
        pDetail.put("Email", emailfs.getText().toString().trim());
        fbfs.collection("Person Data").add(pDetail).addOnCompleteListener(task -> {
            Toast.makeText(this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
            namefs.setText("");
            emailfs.setText("");
        });
    }



}