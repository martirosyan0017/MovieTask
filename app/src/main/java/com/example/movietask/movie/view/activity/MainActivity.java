package com.example.movietask.movie.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.movietask.R;
import com.example.movietask.movie.model.Model;
import com.example.movietask.movie.view.adapter.RecyclerViewAdapter;
import com.example.movietask.movie.viewmodel.MyViewModel;
import com.example.movietask.utils.interfaces.OnClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private MyViewModel viewModel;
    private List<Model> localModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObjects();
        initViewModel();
        initRequest();
    }
    private void initRequest() {
        viewModel.getMovies();
        viewModel.getLiveData()
                .observe(this, new Observer<List<Model>>() {
                    @Override
                    public void onChanged(List<Model> models) {
                        localModels = models;
                        configureRecyclerView(models);
                    }
                });
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
    }

    private void configureRecyclerView(List<Model> models) {
        if (models != null) {
            adapter.setModels(models);
            adapter.notifyDataSetChanged();
        }
    }

    private void initObjects() {
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, new OnClickListener() {
            @Override
            public void onClickItem(int position) {
                Intent intent = new Intent(MainActivity.this,UserActivity.class);
                intent.putExtra("movie", localModels.get(position));
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
