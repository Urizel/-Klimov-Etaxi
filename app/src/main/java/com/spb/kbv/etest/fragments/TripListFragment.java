package com.spb.kbv.etest.fragments;

import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.spb.kbv.etest.infrastructure.EtestApplication;
import com.spb.kbv.etest.R;
import com.spb.kbv.etest.data.Trip;
import com.spb.kbv.etest.data.TripRecycleAdapter;
import com.spb.kbv.etest.infrastructure.Storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripListFragment extends Fragment {

    private ArrayList<Trip> mTripList;
    private TripRecycleAdapter mAdapter;

    private TextView mEmptyData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mAdapter = new TripRecycleAdapter(getActivity());
        mTripList = mAdapter.getTripList();

        EtestApplication mApplication = (EtestApplication) getActivity().getApplication();

        mApplication.getApi().getTrips().enqueue(new Callback<List<Trip>>() {
            @Override
            public void onResponse(Call<List<Trip>> call, Response<List<Trip>> response) {
                mTripList.addAll(response.body());
                Collections.sort(mTripList, Collections.<Trip>reverseOrder());
                mAdapter.notifyDataSetChanged();
                showEmptyData(false);
            }

            @Override
            public void onFailure(Call<List<Trip>> call, Throwable t) {
                Toast.makeText(getActivity(), "Проверьте соединение с сетью", Toast.LENGTH_SHORT)
                        .show();
                showEmptyData(true);
            }
        });
    }

    //Show empty data information
    private void showEmptyData(boolean b) {
        if (mEmptyData != null) {
            mEmptyData.setVisibility(b ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_list_recycler);
        mEmptyData = (TextView) rootView.findViewById(R.id.fragment_list_empty_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);

        if (mTripList.size() > 0) {
            showEmptyData(false);
        }

        return rootView;
    }
}
