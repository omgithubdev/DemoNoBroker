package com.omagrahari.demonobroker.Network;

import com.omagrahari.demonobroker.Model.PropertyListBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by omprakash on 30/06/18.
 */

public interface RetrofitService {
    @GET("api/v1/property/filter/region/ChIJLfyY2E4UrjsRVq4AjI7zgRY/")
    Call<PropertyListBean> getPropertyList(@Query("lat_lng") String lat_lng, @Query("rent") String rent, @Query("travelTime") String travelTime, @Query("pageNo") int pageNo);
}
