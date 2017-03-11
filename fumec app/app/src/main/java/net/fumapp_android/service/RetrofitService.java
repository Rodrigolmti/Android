package net.fumapp_android.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rodrigo Lopes Martins on 11/02/17.
 */

public class RetrofitService {

    private static RetrofitService instance;

    public static RetrofitService getInstance() {

        if (instance == null) {
            instance = new RetrofitService();
        }

        return instance;
    }

    public Retrofit getRetrofit() {

        return new Retrofit.Builder()
                .baseUrl("http://sinapp.amarocorp.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
