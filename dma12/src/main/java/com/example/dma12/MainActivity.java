package com.example.dma12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private FirebaseService firebaseService;
    private List<Movie> movieList = new ArrayList<>();
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    private Callback<List<Movie>> dataChangeCallback()
    {
        return new Callback<List<Movie>>() {
            @Override
            public void runResultOnUiThread(List<Movie> result) {
                if(result != null)
                {
                    movieList.clear();
                    movieList.addAll(result);
                    Log.d(TAG, "Movie" + movieList);
                    movieAdapter = new MovieAdapter(getApplicationContext(), movieList);
                    recyclerView.setAdapter(movieAdapter);
                }
            }
        };
    }

    public void readJsonMovies() {
        String jsonMovieArray = JsonUtil.getJsonFromResource(this, R.raw.movies_json);
        movieList = JsonUtil.readJson(jsonMovieArray);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readJsonMovies();

        recyclerView = findViewById(R.id.recyclerView);

        firebaseService = FirebaseService.getInstance();
        firebaseService.attachDataChangeEventListener(dataChangeCallback());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("movies");

        for(Movie movie: movieList)
        {
            int id = new Random().nextInt(99999999);
            myRef.child("movie_"+id).setValue(movie);
        }

    }
}