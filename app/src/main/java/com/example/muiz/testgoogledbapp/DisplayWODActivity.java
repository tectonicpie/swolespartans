package com.example.muiz.testgoogledbapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.muiz.testgoogledbapp.ImageListAdapter;
import com.example.muiz.testgoogledbapp.LoadFeedData;
import com.example.muiz.testgoogledbapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DisplayWODActivity extends ListActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wod);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mDatabase =  FirebaseDatabase.getInstance().getReference("wods");

        final List<wod> wods = new ArrayList<>();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dbwod: dataSnapshot.getChildren()){
                    wod dbwodValue = dbwod.getValue(wod.class);
                    wods.add(dbwodValue);
                    Log.d("swole", dbwodValue.toString());
                }
                Log.d("swole", Integer.toString(wods.size()));
                Log.d("swole", wods.get(0).exercises.get(0).sets.get(0).reps);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Log.d(LOG_TAG, databaseError.getMessage());
            }
        });

        Log.d("swole", Integer.toString(wods.size()));

        ImageListAdapter adapter = new ImageListAdapter(this);
        setListAdapter(adapter);

        LoadFeedData loadFeedData = new LoadFeedData(adapter);
        loadFeedData.execute();
    }
}