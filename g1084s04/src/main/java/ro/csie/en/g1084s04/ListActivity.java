package ro.csie.en.g1084s04;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListActivity extends AppCompatActivity {

    static List<Movie> movieList = new ArrayList<>();
    RecyclerView recyclerView;
    ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        DatabaseManager databaseManager = DatabaseManager.getInstance(this);
        MovieDao movieDao = databaseManager.getMovieDao();

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        ArrayList<Movie> movies = extras.getParcelableArrayList("movies");
        if(movies != null && movies.size() > 0)
        {
            movieList.addAll(movies);
        }
        else
        {
            Movie movie = extras.getParcelable("movie");
            movieList.add(movie);
        }
        recyclerView = findViewById(R.id.rvMovies);
        executorService = Executors.newFixedThreadPool(4);
        MovieAdapter movieAdapter = new MovieAdapter(this, movieList, executorService, movieDao);
        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        executorService.shutdown();
    }
}