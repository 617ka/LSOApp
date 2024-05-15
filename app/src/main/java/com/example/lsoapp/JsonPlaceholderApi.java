package com.example.lsoapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface JsonPlaceholderApi {
    @GET("czlonkowie")
    Call<List<DodanieSluzby>> getCzlonkow();

}
