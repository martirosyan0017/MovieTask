package com.example.movietask.movie.viewmodel.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.movietask.movie.model.MovieModel;
import com.example.movietask.webservice.ApiService;
import com.example.movietask.webservice.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private MutableLiveData<List<MovieModel>> modelMutableLiveData;

    public MovieRepository(MutableLiveData<List<MovieModel>> modelMutableLiveData) {
        this.modelMutableLiveData = modelMutableLiveData;
    }

    public MutableLiveData<List<MovieModel>> initRequest() {

        RetrofitClient
                .getInstance()
                .createMovieService()
                .getData()
                .enqueue(new Callback<List<MovieModel>>() {
                    @Override
                    public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                        if (response.isSuccessful()) {
                            modelMutableLiveData.setValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                        modelMutableLiveData.setValue(null);
                    }
                });
        return modelMutableLiveData;
    }
}
