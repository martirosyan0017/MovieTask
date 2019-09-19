package com.example.movietask.movie.viewmodel;


import androidx.lifecycle.MutableLiveData;

import com.example.movietask.movie.model.Model;
import com.example.movietask.utils.webservice.ApiService;
import com.example.movietask.utils.webservice.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRepository {
    private MutableLiveData<List<Model>> modelMutableLiveData;
    private RetrofitClient retrofitClient = new RetrofitClient();

    public MyRepository(MutableLiveData<List<Model>> modelMutableLiveData) {
        this.modelMutableLiveData = modelMutableLiveData;
    }

    public MutableLiveData<List<Model>> initRequest() {
        ApiService service = retrofitClient.initRetrofit().create(ApiService.class);
        service
                .getData()
                .enqueue(new Callback<List<Model>>() {
                    @Override
                    public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                        if (response.isSuccessful()) {
                            modelMutableLiveData.setValue(response.body());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Model>> call, Throwable t) {
                        modelMutableLiveData.setValue(null);
                    }
                });
        return modelMutableLiveData;
    }
}
