package com.example.guest.moviediscovery.services;

import android.util.Log;

import com.example.guest.moviediscovery.Constants;
import com.example.guest.moviediscovery.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieService {

    public static void findMovies(String keyword, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.API_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.KEYWORD_QUERY_PARAMETER, keyword);
        urlBuilder.addQueryParameter(Constants.API_QUERY_PARAMETER, Constants.API_KEY);
        String url = urlBuilder.build().toString();
        Log.d("log", "url: " + url);
        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Movie> processResults(Response response) {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject movieJSON = new JSONObject(jsonData);
                JSONArray allJSON = movieJSON.getJSONArray("results");


                for(int i = 0; i < allJSON.length(); i ++) {
                    JSONObject thisResults = allJSON.getJSONObject(i);
                    String title = thisResults.getString("title");

                    String poster_path = thisResults.getString("poster_path");
                    if (poster_path.equals("null")) {
                        poster_path = "http://www.freepostertemplates.co.uk/wp-content/previews/poster-template-movie-poster.jpg";
                    } else {
                        poster_path = "https://image.tmdb.org/t/p/w500" + thisResults.getString("poster_path");
                    }

                    String overview = thisResults.getString("overview");
                    String release_date = thisResults.getString("release_date");

                    Movie movie = new Movie(title, poster_path, overview, release_date);
                    movies.add(movie);
                }
                Log.d("log", movies.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;

    }


    // title, poster_path (if null), overview, release_date

}
