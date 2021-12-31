package com.pratik.practiceeverything;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DbAdapter extends ArrayAdapter<DatabaseList.Dbholder> {
    Activity context;
    List<DatabaseList.Dbholder> dblist;

    public DbAdapter(Activity context1, List<DatabaseList.Dbholder> dblist) {
        super(context1, R.layout.dbinfo, dblist);
        this.context = context1;
        this.dblist = dblist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.dbinfo, null, true);

        TextView name = view.findViewById(R.id.dbnameshow);
        TextView classname = view.findViewById(R.id.dbclassshow);

        DatabaseList.Dbholder holder = dblist.get(position);
        name.setText(holder.getName());
        classname.setText(holder.getClassname());
        return view;
    }
}
