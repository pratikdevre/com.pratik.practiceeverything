package com.pratik.practiceeverything;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseList extends AppCompatActivity {

    EditText dbname, dbclass;
    FirebaseDatabase database;
    DatabaseReference reference;
    ListView listView;
    List<Dbholder> list;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_list);
        dbname = findViewById(R.id.dbname);
        dbclass = findViewById(R.id.dbclass);
        list = new ArrayList<>();
        listView = findViewById(R.id.dblistview);
        database = FirebaseDatabase.getInstance();
        bar = findViewById(R.id.progressBar);

        listView.setAdapter(new DbAdapter(DatabaseList.this, list));

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Dbholder dbholder = list.get(position);
            Intent intent = new Intent(DatabaseList.this, DbInfo.class);
            intent.putExtra("name", dbholder.getName());
            intent.putExtra("id", dbholder.getId());
            intent.putExtra("classname", dbholder.getClassname());
            startActivity(intent);
        });
        listView.setOnItemLongClickListener((parent, view, i, id) -> {
                    Dbholder dbholder = list.get(i);
                    updateData(dbholder.getId(), dbholder.name);
                    return false;
                }
        );
    }

    private void updateData(String idd, String nme) {
        View view = getLayoutInflater().inflate(R.layout.showalert, null);
        AlertDialog builder;
        builder = new AlertDialog.Builder(this)
                .setView(view)
                .setTitle("Update or Delete the " + nme)
                .show();
        EditText name = view.findViewById(R.id.updatename);
        EditText cname = view.findViewById(R.id.updateclass);
        Button update = view.findViewById(R.id.updatedata);
        Button delete = view.findViewById(R.id.deletedata);
        update.setOnClickListener(v -> {
            reference = database.getReference("dbinfo").child(idd);
            reference.setValue(
                    new Dbholder(name.getText().toString(), cname.getText().toString(), idd)
            );
            Toast.makeText(this, "Data Updated Successfully.", Toast.LENGTH_SHORT).show();
            builder.dismiss();

        });

        delete.setOnClickListener(v -> {
            reference = database.getReference("something").child(idd);
            reference.removeValue();
            reference = database.getReference("dbinfo").child(idd);
            reference.removeValue();
            Toast.makeText(this, "Data Deleted Successfully.", Toast.LENGTH_SHORT).show();
            builder.dismiss();

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        bar.setVisibility(View.VISIBLE);
        reference = database.getReference("dbinfo");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    Dbholder dbholder = snap.getValue(Dbholder.class);
                    list.add(dbholder);
                }
                bar.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_SHORT).show();
                bar.setVisibility(View.GONE);
            }
        });

    }

    public void addToDB(View view) {
        String name = dbname.getText().toString();
        String classname = dbclass.getText().toString();
        String id;

        reference = database.getReference("dbinfo");
        id = reference.push().getKey();
        Dbholder dbholder = new Dbholder(name, classname, id);
        reference.child(id).setValue(dbholder);
        Toast.makeText(this, "Added Successfully...", Toast.LENGTH_SHORT).show();

    }

    //    database holder class don't touch it
    public static class Dbholder {
        String name, classname, id;

        public Dbholder() {
        }

        public Dbholder(String name, String classname, String id) {
            this.name = name;
            this.classname = classname;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public String getClassname() {
            return classname;
        }

        public String getId() {
            return id;
        }
    }
}