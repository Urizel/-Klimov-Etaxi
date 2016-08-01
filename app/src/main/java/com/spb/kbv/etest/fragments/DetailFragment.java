package com.spb.kbv.etest.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spb.kbv.etest.infrastructure.Storage;
import com.spb.kbv.etest.infrastructure.ImageDownLoader;
import com.spb.kbv.etest.R;
import com.spb.kbv.etest.data.Trip;

public class DetailFragment extends Fragment {

    private Trip mTrip;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mTrip = Storage.getInstance().getTrip();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        TextView route = (TextView) rootView.findViewById(R.id.list_item_route);
        TextView price = (TextView) rootView.findViewById(R.id.list_item_price);
        TextView date = (TextView) rootView.findViewById(R.id.list_item_date);
        TextView time = (TextView) rootView.findViewById(R.id.fragment_details_time);
        TextView driver = (TextView) rootView.findViewById(R.id.fragment_details_driver);
        TextView car = (TextView) rootView.findViewById(R.id.fragment_details_car);
        ImageView image = (ImageView) rootView.findViewById(R.id.fragment_details_picture);

        if (mTrip != null) {
            ImageDownLoader imageDownloader = new ImageDownLoader();
            imageDownloader.download(image, mTrip.getVehicle().getPhoto(), getActivity());

            route.setText(mTrip.getRoute());
            price.setText(mTrip.getPriceString());
            date.setText(mTrip.getDateString());
            time.setText(mTrip.getTimeString());
            driver.setText(mTrip.getVehicle().getDriverName());
            car.setText(mTrip.getVehicleString());
        }

        return rootView;
    }
}
