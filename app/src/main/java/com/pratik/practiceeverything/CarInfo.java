package com.pratik.practiceeverything;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CarInfo extends AppCompatActivity {
    ImageView carimg;
    TextView carname;
    String key, img, name;
    FirebaseDatabase fd;
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        carimg = findViewById(R.id.showcarimageinfo);
        carname = findViewById(R.id.showcarnameinfo);
        key = getIntent().getStringExtra("key");
        fd = FirebaseDatabase.getInstance();
        dr = fd.getReference("Cars Info").child(key);
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                img = snapshot.child("_carimage").toString();
                name = snapshot.child("_carname").toString();

                Glide.with(getApplicationContext()).load(img).into(carimg);
                carname.setText(name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void deleteCar(View view) {
        
        dr.removeValue();
        Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}