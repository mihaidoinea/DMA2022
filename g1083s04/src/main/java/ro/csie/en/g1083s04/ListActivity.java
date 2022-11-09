package ro.csie.en.g1083s04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ListActivity extends AppCompatActivity {

    static List<Movie> movieList = new ArrayList<>();
    RecyclerView recyclerView;
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Movie movie = extras.getParcelable("movie");
        movieList.add(movie);

        recyclerView = findViewById(R.id.rvMovies);
        executorService = Executors.newFixedThreadPool(4);
        MovieAdapter movieAdapter = new MovieAdapter(this, movieList, executorService);
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        executorService.shutdown();
    }
}