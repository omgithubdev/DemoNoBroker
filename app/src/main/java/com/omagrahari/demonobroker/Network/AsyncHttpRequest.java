package com.omagrahari.demonobroker.Network;

import android.app.Activity;
import android.widget.Toast;

import com.omagrahari.demonobroker.Utilities.CheckInternetConnection;
import com.omagrahari.demonobroker.Utilities.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by omprakash on 29/06/18.
 */

public class AsyncHttpRequest {

    public static Retrofit newRetrofitRequest(Activity activity) {
        Retrofit retrofit = null;
        if (CheckInternetConnection.isNetworkAvailable(activity)) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5, java.util.concurrent.TimeUnit.MINUTES).readTimeout(5, java.util.concurrent.TimeUnit.MINUTES).addInterceptor(interceptor).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit;
        } else {
            //SHOW NO INTERNET MESSAGE
            Toast.makeText(activity, "Please check your internet connection", Toast.LENGTH_LONG).show();
        }

        return retrofit;
    }
}
