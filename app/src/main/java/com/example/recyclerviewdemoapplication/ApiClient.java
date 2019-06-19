package com.example.recyclerviewdemoapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {

    String BASE_URL = "http://api.myjson.com/bins/";
    @GET("d5y1e")
    Call<List<Product>> getProducts();
}
