package com.pratik.practiceeverything;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Objects;

public class OpenCamera extends AppCompatActivity {

    Button opencamera;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_camera);
        opencamera = findViewById(R.id.open_camera);
        image = findViewById(R.id.capturedimage);
        opencamera.setOnClickListener(v->{
            getImage.launch("image/*");
        });
    }

    ActivityResultLauncher<String> getImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            if (result != null){
                image.setImageURI(result);
            }
        }
    });

}
//  opencamera = findViewById(R.id.open_camera);
//          image = findViewById(R.id.capturedimage);
//          activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
//          if (result.getResultCode() == RESULT_OK && result.getData() != null){
//          Bundle bundle = result.getData().getExtras();
//          Bitmap bitmap = (Bitmap) bundle.get("data");
//          image.setImageBitmap(bitmap);
//          }
//          });
//          opencamera.setOnClickListener(v->{
//          Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//          activityResultLauncher.launch(intent);
//          });