package com.example.guest.moviediscovery.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.guest.moviediscovery.R;
import com.example.guest.moviediscovery.models.Movie;
import com.example.guest.moviediscovery.services.MovieService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MoviesActivity extends AppCompatActivity {
    @Bind(R.id.keywordTextView) TextView mKeywordTextView;
    private ArrayList<Movie> mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String keyword = intent.getStringExtra("keyword");
        getMovies(keyword);
        mKeywordTextView.setText("You searched for: " + keyword);

    }

    private void getMovies(String keyword) {
        final MovieService movieService = new MovieService();
        movieService.findMovies(keyword, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mMovies = movieService.processResults(response);
                Log.d("log", "onResponse: " + mMovies.toString());
            }
        });
    }
}
