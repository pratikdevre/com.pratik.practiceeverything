package com.pratik.practiceeverything;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CarAdapter extends FirebaseRecyclerAdapter<NewCarCreate.NewCarCreateModel, CarAdapter.MyViewHolder> {
    Context context;

    public CarAdapter(@NonNull FirebaseRecyclerOptions<NewCarCreate.NewCarCreateModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull NewCarCreate.NewCarCreateModel model)
    {

        holder.showcarname.setText(model.get_carname());
        Glide.with(context).load(model.get_carimage()).into(holder.showcarimage);

        holder.v.setOnClickListener(v->{
            context.startActivity(new Intent(context, CarInfo.class)
            .putExtra("img", model.get_carimage())
                    .putExtra("key", getRef(position).getKey())
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        });

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_layout, parent, false);

        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView showcarimage;
        TextView showcarname;
        View v;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            showcarimage = itemView.findViewById(R.id.showcarimage);
            showcarname = itemView.findViewById(R.id.showcarname);
            v = itemView;
        }
    }
}
