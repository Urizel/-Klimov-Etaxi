package com.spb.kbv.etest.infrastructure;

import com.spb.kbv.etest.data.Trip;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {

    @GET("orders.json")
    Call<List<Trip>> getTrips();

    @GET("images/{imageName}")
    Call<ResponseBody> getImage(@Path("imageName") String imageName);

}
