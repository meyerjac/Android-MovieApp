package com.example.guest.moviediscovery.models;

/**
 * Created by Guest on 12/1/16.
 */
public class Movie {
    private String mTitle;
    private String mPoster_path;
    private String mOverview;
    private String mRelease_date;

    public Movie(String mTitle, String mPoster_path, String mOverview, String mRelease_date) {
        this.mTitle = mTitle;
        this.mPoster_path = mPoster_path;
        this.mOverview = mOverview;
        this.mRelease_date = mRelease_date;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPoster_path() {
        return mPoster_path;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getRelease_date() {
        return mRelease_date;
    }
}


// title, poster_path (if null), overview, release_date