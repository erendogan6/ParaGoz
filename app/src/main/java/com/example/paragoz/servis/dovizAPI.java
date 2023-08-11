package com.example.paragoz.servis;

import com.google.gson.JsonObject;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface dovizAPI {
    @Headers({
            "Content-Type: application/json",
            "Authorization: apikey 43kZSPiJiaWemyOHFhTs4v:5dAt3aWWmhLKVpiFqDYCtD"
    })
    @GET("economy/allCurrency")
    Observable<JsonObject> getData();
}
