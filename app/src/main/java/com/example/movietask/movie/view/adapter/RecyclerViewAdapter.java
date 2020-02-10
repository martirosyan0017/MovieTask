package com.example.movietask.movie.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movietask.R;
import com.example.movietask.movie.model.MovieModel;
import com.example.movietask.listener.OnClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private OnClickListener onClickListener;
    private List<MovieModel> movieModels;
    private Context context;


    public RecyclerViewAdapter(Context context, OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    public void setMovieModels(List<MovieModel> movies) {
        this.movieModels = movies;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.bind(movieModels.get(position));
    }

    @Override
    public int getItemCount() {
        if (this.movieModels != null) {
            return this.movieModels.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView rating;
        private TextView releaseYear;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            findViews(itemView);
            onClick();
        }
        private void findViews(View itemView){
            imageView = itemView.findViewById(R.id.image_main);
            title = itemView.findViewById(R.id.title_main);
            rating = itemView.findViewById(R.id.rating_main);
            releaseYear = itemView.findViewById(R.id.release_Year_main);
        }
        private void onClick(){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClickItem(getAdapterPosition());
                }
            });
        }
        private void bind(MovieModel movieModels){
            title.setText(("  Title : " + movieModels.getTitle()));
            rating.setText(("  Rating : " + movieModels.getRating()));
            releaseYear.setText(("  Release Year : " + movieModels.getReleaseYear()));
            Picasso.get().load(movieModels.getImage()).into(imageView);
        }
    }
}

