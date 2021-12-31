package com.pratik.practiceeverything;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsingFireStore extends AppCompatActivity {
    Button addbtnfs, fetchbtnfs;
    EditText namefs, emailfs;
    FirebaseFirestore fbfs;
    RecyclerView rcvfetchedfs;
    DataAdapter adapter;
    ArrayList<DataModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_using_fire_store);
        addbtnfs = findViewById(R.id.addbtnfs);
        fetchbtnfs = findViewById(R.id.fetchbtnfs);
        rcvfetchedfs = findViewById(R.id.rcvfetchedfs);
        namefs = findViewById(R.id.namefs);
        emailfs = findViewById(R.id.emailfs);

        fbfs = FirebaseFirestore.getInstance();

        addbtnfs.setOnClickListener(v -> {
            insertData();
        });
        fetchData();

        rcvfetchedfs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        adapter = new DataAdapter(list);
        rcvfetchedfs.setAdapter(adapter);
    }



//      Toast.makeText(this, "fetching", Toast.LENGTH_SHORT).show();
//        fbfs.collection("Person Data").document("DdjY4bm8jJn0BVyeRBL9").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//        @Override
//        public void onSuccess(DocumentSnapshot documentSnapshot) {
//
//            Log.d("TAGsc","success "+documentSnapshot.exists());
////                Log.d("TAGsc","email : "+documentSnapshot.getString("Email"));
////                Log.d("TAGsc","Name : "+documentSnapshot.getString("Name"));
//
//        }
//    }).addOnFailureListener(new OnFailureListener() {
//        @Override
//        public void onFailure(@NonNull Exception e) {
//            Log.d("TAGsc","fail ");
//        }
//    });
    private void fetchData() {
        fbfs.collection("Person Data")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("jbj", "doc id > "+document.getId() + " => " + document.getData());


                            }
                        } else {
                            Log.d("jbj", "Error getting documents: ", task.getException());
                        }
                    }
                });

//        fbfs.collection("Person Data").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//
//
//                List<DocumentSnapshot> list2 = queryDocumentSnapshots.getDocuments();
//                for (DocumentSnapshot q : list2) {
//
//                    Log.d("dvdv",""+q.toObject(DataModel.class).email);
//                    list.add(q.toObject(DataModel.class));
//                    Log.d("TAGsc","success email : "+list.get(0).email);
//
//                }
//
//                adapter.notifyDataSetChanged();
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d("TAGsc","failure "+e.getMessage());
//                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        fbfs.collection("Person Data").document("DdjY4bm8jJn0BVyeRBL9").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                Log.d("TAGsc","success ");
//                List<DocumentSnapshot> list2 =queryDocumentSnapshots.getDocuments();
//                Log.d("TAGsc","success "+list);
////                List<DocumentSnapshot> list2 = queryDocumentSnapshots.getDocuments();
////                for (DocumentSnapshot q : list2) {
////                    list.add(q.toObject(DataModel.class));
////                }
//                adapter.notifyDataSetChanged();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d("TAGsc","failure "+e.getMessage());
//                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

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

    public static class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolderfs> {
        ArrayList<DataModel> dataList;

        public DataAdapter(ArrayList<DataModel> dataList) {
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public MyViewHolderfs onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fs_data_layout, parent, false);
            return new MyViewHolderfs(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolderfs holder, int position) {
            holder.nametextviewfs.setText(dataList.get(position).getName());
            holder.emailtextviewfs.setText(dataList.get(position).getEmail());
            Log.d("dfvflmv","position>"+position);
            Log.d("dfvflmv","getName"+dataList.get(position).getName());
            Log.d("dfvflmv","getEmail"+dataList.get(position).getEmail());
            Log.d("dfvflmv","........................");

        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public class MyViewHolderfs extends RecyclerView.ViewHolder {
            TextView nametextviewfs, emailtextviewfs;

            public MyViewHolderfs(@NonNull View itemView) {
                super(itemView);
                nametextviewfs = itemView.findViewById(R.id.nametextviewfs);
                emailtextviewfs = itemView.findViewById(R.id.emailtextviewfs);
            }
        }
    }

    public static class DataModel {
        String name;
        String email;

        public DataModel() {
        }

        public DataModel(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}