package com.spb.kbv.etest.infrastructure;

import com.spb.kbv.etest.data.Trip;


//Make storage for Trip.class object while activity interaction
public class Storage {

    private static Storage storage;
    private Trip mtrip;

    private Storage() {
    }

    public static Storage getInstance(){
        if (storage == null)
            storage = new Storage();
        return storage;
    }

    public Trip getTrip (){
            return mtrip;
    }

    public void addTripToStorage(Trip trip) {
        this.mtrip = trip;
    }
}
