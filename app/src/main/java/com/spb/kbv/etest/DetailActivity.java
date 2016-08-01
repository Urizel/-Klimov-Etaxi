package com.spb.kbv.etest;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.spb.kbv.etest.fragments.DetailFragment;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Детали поездки");

        if (savedInstanceState == null) {
            DetailFragment fragment = new DetailFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.trip_details_container, fragment)
                    .commit();
        }
    }
}
