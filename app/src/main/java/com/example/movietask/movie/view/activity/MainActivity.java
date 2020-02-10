package com.example.movietask.movie.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.movietask.R;
import com.example.movietask.movie.model.MovieModel;
import com.example.movietask.movie.view.adapter.RecyclerViewAdapter;
import com.example.movietask.movie.viewmodel.MovieViewModel;
import com.example.movietask.listener.OnClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter adapter;
    private MovieViewModel viewModel;
    private List<MovieModel> localMovieModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        initViewModel();
        initRequest();
    }

    private void initRequest() {
        viewModel.getMovies();
        viewModel.getLiveData()
                .observe(this, new Observer<List<MovieModel>>() {
                    @Override
                    public void onChanged(List<MovieModel> movieModels) {
                        localMovieModels = movieModels;
                        configureRecyclerView(movieModels);
                    }
                });
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
    }

    private void configureRecyclerView(List<MovieModel> movieModels) {
        if (movieModels != null) {
            adapter.setMovieModels(movieModels);
            adapter.notifyDataSetChanged();
        }
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, new OnClickListener() {
            @Override
            public void onClickItem(int position) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("movie", localMovieModels.get(position));
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
