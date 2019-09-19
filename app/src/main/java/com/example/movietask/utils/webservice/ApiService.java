package com.example.movietask.utils.webservice;

import com.example.movietask.movie.model.Model;
import com.example.movietask.utils.interfaces.AppConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET(AppConstants.END_POINT)
    Call<List<Model>> getData();
}