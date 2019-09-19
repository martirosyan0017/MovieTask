package com.example.movietask.movie.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movietask.R;
import com.example.movietask.movie.model.Model;
import com.example.movietask.utils.interfaces.OnClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private OnClickListener onClickListener;
    private List<Model> models;
    private Context context;

    public RecyclerViewAdapter(Context context, OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    public void setModels(List<Model> movies) {
        this.models = movies;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, final int position) {

        holder.title.setText(("  Title : " + models.get(position).getTitle()));
        holder.rating.setText(("  Rating : " + models.get(position).getRating()));
        holder.releaseYear.setText(("  Release Year : " + models.get(position).getReleaseYear()));

        Picasso.get().load(models.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (this.models != null) {
            return this.models.size();
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
            imageView = itemView.findViewById(R.id.image_main);
            title = itemView.findViewById(R.id.title_main);
            rating = itemView.findViewById(R.id.rating_main);
            releaseYear = itemView.findViewById(R.id.release_Year_main);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.onClickItem(getAdapterPosition());
                }
            });
        }
    }
}

