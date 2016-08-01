package com.spb.kbv.etest.data;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spb.kbv.etest.R;

import java.util.ArrayList;

public class TripRecycleAdapter extends RecyclerView.Adapter<TripRecycleAdapter.ViewHolder> {

    private TripSelectListener mListener;
    private ArrayList<Trip> mTrips;

    public TripRecycleAdapter(Activity activity) {
        mListener = (TripSelectListener)activity;
        mTrips = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Trip trip = mTrips.get(position);
        holder.route.setText(trip.getRoute());
        holder.price.setText(trip.getPriceString());
        holder.date.setText(trip.getDateString());
        holder.itemBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.showTripInfo(trip);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTrips.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public View itemBackground;
        public TextView route;
        public TextView price;
        public TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            itemBackground = (View) itemView.findViewById(R.id.item_background);
            route = (TextView) itemView.findViewById(R.id.list_item_route);
            price = (TextView) itemView.findViewById(R.id.list_item_price);
            date = (TextView) itemView.findViewById(R.id.list_item_date);
        }
    }

    //Interface for fragments interaction via activity
    public interface TripSelectListener {
        public void showTripInfo(Trip trip);
    }

    public ArrayList<Trip> getTripList() {
        return mTrips;
    }

}
