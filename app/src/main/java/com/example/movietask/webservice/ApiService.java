package com.example.movietask.webservice;

import com.example.movietask.movie.model.MovieModel;
import com.example.movietask.constants.AppConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET(AppConstants.END_POINT)
    Call<List<MovieModel>> getData();
}