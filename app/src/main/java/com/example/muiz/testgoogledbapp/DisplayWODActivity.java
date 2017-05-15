package com.example.muiz.testgoogledbapp;

import android.app.ListActivity;
import android.os.Bundle;

import com.example.muiz.testgoogledbapp.ImageListAdapter;
import com.example.muiz.testgoogledbapp.LoadFeedData;
import com.example.muiz.testgoogledbapp.R;

public class DisplayWODActivity extends ListActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wod);

        ImageListAdapter adapter = new ImageListAdapter(this);
        setListAdapter(adapter);

        LoadFeedData loadFeedData = new LoadFeedData(adapter);
        loadFeedData.execute();
    }
}