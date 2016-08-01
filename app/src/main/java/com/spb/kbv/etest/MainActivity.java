package com.spb.kbv.etest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.spb.kbv.etest.infrastructure.Storage;
import com.spb.kbv.etest.data.Trip;
import com.spb.kbv.etest.data.TripRecycleAdapter;

public class MainActivity extends AppCompatActivity implements TripRecycleAdapter.TripSelectListener {

    private Storage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Ваши поездки");

        mStorage = Storage.getInstance();
        if (mStorage.getTrip() != null) {
            mStorage.addTripToStorage(null);
        }
    }

    @Override
    public void showTripInfo(Trip trip) {
        mStorage.addTripToStorage(trip);
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }
}
