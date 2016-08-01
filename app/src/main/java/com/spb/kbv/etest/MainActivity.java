package com.spb.kbv.etest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.spb.kbv.etest.infrastructure.Storage;
import com.spb.kbv.etest.data.Trip;
import com.spb.kbv.etest.data.TripRecycleAdapter;

public class MainActivity extends AppCompatActivity implements TripRecycleAdapter.TripSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Ваши поездки");
    }

    @Override
    public void showTripInfo(Trip trip) {
        Storage storage = Storage.getInstance();
        storage.addTripToStorage(trip);
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}
