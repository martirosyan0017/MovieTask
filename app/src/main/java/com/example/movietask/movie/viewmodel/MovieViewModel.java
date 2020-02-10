package com.example.movietask.movie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movietask.movie.model.MovieModel;
import com.example.movietask.movie.viewmodel.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    // Live data
    private MutableLiveData<List<MovieModel>> modelMutableLiveData = new MutableLiveData<>();
    // repository
    private MovieRepository movieRepository;

    public void getMovies() {
        if (movieRepository == null) {
            movieRepository = new MovieRepository(modelMutableLiveData);
        }
        modelMutableLiveData = movieRepository.initRequest();
    }

    public LiveData<List<MovieModel>> getLiveData() {
        return modelMutableLiveData;
    }
}
