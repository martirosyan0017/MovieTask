package com.example.movietask.movie.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movietask.R;
import com.example.movietask.movie.model.MovieModel;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleText;
    private TextView ratingText;
    private TextView releaseYearText;
    private TextView genreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        findViews();
        getData();
    }

    private void findViews() {
        imageView = findViewById(R.id.user_image);
        titleText = findViewById(R.id.my_user_text1);
        ratingText = findViewById(R.id.my_user_text2);
        genreText = findViewById(R.id.my_user_text4);
        releaseYearText = findViewById(R.id.my_user_text3);
    }

    public void getData() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("movie")) {
            MovieModel movie = intent.getParcelableExtra("movie");
            if (movie != null) {
                titleText.setText(("  Title : " + movie.getTitle()));
                ratingText.setText(("  Rating : " + movie.getRating()));
                releaseYearText.setText(("  Release Year : " + movie.getReleaseYear()));

                StringBuilder builder = new StringBuilder();
                for(String s : movie.getGenre()){
                    builder.append(s);
                }
                genreText.setText(builder.toString());
                Glide.with(this).load(movie.getImage()).into(imageView);
            }
        }
    }
}
