package com.example.movietask.movie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movietask.movie.model.Model;

import java.util.List;

public class MyViewModel extends ViewModel {

    private MutableLiveData<List<Model>> modelMutableLiveData = new MutableLiveData<>();
    private MyRepository myRepository = new MyRepository(modelMutableLiveData);

    public void getMovies() {
        modelMutableLiveData = myRepository.initRequest();
    }

    public LiveData<List<Model>> getLiveData() {
        return modelMutableLiveData;
    }
}
