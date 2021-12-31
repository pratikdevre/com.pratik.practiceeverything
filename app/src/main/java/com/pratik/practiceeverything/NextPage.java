package com.pratik.practiceeverything;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.UUID;

public class NextPage extends AppCompatActivity {
    EditText dprole, dpname, dpage, dpphn;
    ImageView dp;
    ImageButton dpbrowse;
    Button adddb;
    Uri imageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_page);
        dp = findViewById(R.id.dp);
        dpbrowse = findViewById(R.id.dpbrowse);


        dprole = findViewById(R.id.dprole);
        dpname = findViewById(R.id.dpname);
        dpage = findViewById(R.id.dpage);
        dpphn = findViewById(R.id.dpphn);

        adddb = findViewById(R.id.adddb);

//        taking user permission in this code
        dpbrowse.setOnClickListener(v -> {
            Dexter.withContext(this)
                    .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                            getDPImage.launch("image/*");
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                            permissionToken.continuePermissionRequest();
                        }
                    })
                    .check();
        });

        adddb.setOnClickListener(v -> {
            addValueToData();
        });

    }

    //    getting image from storage in this code
    ActivityResultLauncher<String> getDPImage = registerForActivityResult(new ActivityResultContracts.GetContent(), result -> {
        if (result != null) {
            dp.setImageURI(result);
            imageURI = result;
        }
    });

    private void addValueToData() {
        String role = dprole.getText().toString();
        String name = dpname.getText().toString();
        String age = dpage.getText().toString();
        String phn = dpphn.getText().toString();
        if (imageURI != null) {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Data Uploader");
            progressDialog.show();

            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            StorageReference reference = firebaseStorage.getReference("images/" + UUID.randomUUID().toString());
            reference.putFile(imageURI)
                    .addOnCompleteListener(task -> {
                        reference.getDownloadUrl().addOnCompleteListener(task1 -> {
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            DatabaseReference databaseReference = firebaseDatabase.getReference("familyMember");
                            DataHolderDB dataHolderDB = new DataHolderDB(name, age, phn, task1.toString());
                            databaseReference.child(role).setValue(dataHolderDB);
                        });
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Data Added Successfully.", Toast.LENGTH_SHORT).show();
                    })
                    .addOnProgressListener(snapshot -> {
                        float percent = (snapshot.getBytesTransferred() * 100) / snapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploading: " + (int) percent + "%");
                    });
        } else
            Toast.makeText(getApplicationContext(), "Please Select Image", Toast.LENGTH_SHORT).show();

    }

}