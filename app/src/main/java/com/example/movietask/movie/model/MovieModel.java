package com.example.movietask.movie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieModel implements Parcelable {
    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("rating")
    @Expose
    private float rating;
    @SerializedName("releaseYear")
    @Expose
    private int releaseYear;
    @SerializedName("genre")
    @Expose
    private List<String> genre = new ArrayList<>();

    protected MovieModel(Parcel in) {
        String[] arr = new String[2];
        in.readStringArray(arr);
        in.readStringList(genre);
        title = arr[0];
        image = arr[1];
        releaseYear = in.readInt();
        rating = in.readFloat();

    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public float getRating() {
        return rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public List<String> getGenre() {
        return genre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        String[] arr = new String[2];
        arr[0] = title;
        arr[1] = image;
        dest.writeStringArray(arr);
        dest.writeStringList(genre);
        dest.writeInt(releaseYear);
        dest.writeFloat(rating);
    }
}


