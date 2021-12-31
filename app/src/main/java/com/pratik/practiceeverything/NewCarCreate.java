package com.pratik.practiceeverything;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;
import java.util.UUID;

public class NewCarCreate extends AppCompatActivity {
    ImageView carimage;
    EditText carname;
    Uri imguri;
    FirebaseDatabase fbdb = FirebaseDatabase.getInstance();
    FirebaseStorage fbs = FirebaseStorage.getInstance();
    StorageReference referenceCarImg;
    DatabaseReference referenceCarDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car_create);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Create new car");

        carimage = findViewById(R.id.carimage);
        carname = findViewById(R.id.carname);
        carimage.setOnClickListener(v -> {
            activityResultLauncher.launch("image/*");
        });
    }

    ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null) {
                        carimage.setImageURI(result);
                        imguri = result;
                    }
                }
            });


    public void addNewCar(View view) {
        String carnme = carname.getText().toString().trim();
        ProgressBar pbar = findViewById(R.id.progbar);
        if (imguri != null && carnme != null) {
            pbar.setVisibility(View.VISIBLE);
            referenceCarImg = fbs.getReference("car_images/image-" + UUID.randomUUID().toString());
            referenceCarDetail = fbdb.getReference("Cars Info");
            String id = referenceCarDetail.push().getKey();
            referenceCarImg.putFile(imguri)
                .addOnSuccessListener(taskSnapshot ->
                    referenceCarImg.getDownloadUrl()
                        .addOnSuccessListener(uri ->
                            referenceCarDetail.child(id)
                                .setValue(new NewCarCreateModel(carnme, uri.toString()))
                                .addOnSuccessListener(unused -> {
                                    pbar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(NewCarCreate.this.getApplicationContext(),
                                            "Data Added Successfully", Toast.LENGTH_SHORT)
                                            .show();
//                                    startActivity(new Intent(this, MainActivityCarProject.class));
                                    finish();
                                })
                                .addOnFailureListener(e -> {
                                    pbar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(NewCarCreate.this.getApplicationContext(),
                                            "Data 3 not Added", Toast.LENGTH_SHORT)
                                            .show();
                                })
                        )
                        .addOnFailureListener(e -> {
                            pbar.setVisibility(View.INVISIBLE);
                            Toast.makeText(NewCarCreate.this.getApplicationContext(),
                                    "Data 2 not Added", Toast.LENGTH_SHORT)
                                    .show();
                        })
                    )
                    .addOnFailureListener(e -> {
                        pbar.setVisibility(View.INVISIBLE);
                        Toast.makeText(NewCarCreate.this.getApplicationContext(),
                                "Data 1 not Added", Toast.LENGTH_SHORT)
                                .show();
                    });

        } else Toast.makeText(this, "Please Select Image and Name", Toast.LENGTH_SHORT).show();
    }

    public static class NewCarCreateModel {
        String _carname, _carimage;

        public NewCarCreateModel() {}

        public NewCarCreateModel(String _carname, String _carimage) {
            this._carname = _carname;
            this._carimage = _carimage;
        }

        public String get_carname() {
            return _carname;
        }

        public String get_carimage() {
            return _carimage;
        }
    }
}