package ro.csie.en.g1083s04;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave, btnRelease;
    private EditText etTitle;
    private Spinner spGenre;
    private SeekBar sbDuration;
    private Movie movie;
    private String TAG = MainActivity.class.getSimpleName();

    public void readJson(View view) {
        String jsonMovieArray = JsonUtil.getJsonFromResource(this, R.raw.movies_json);
        Log.d(TAG, "Json movies: " +jsonMovieArray);
        ArrayList<Movie> movies = JsonUtil.readJson(jsonMovieArray);

        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putParcelableArrayListExtra("movies", movies);
        startActivity(intent);

    }

    public void viewChart(View view) {

        DatabaseManager db = DatabaseManager.getInstance(this);
        MovieDao movieDao = db.getMovieDao();
        List<Movie> movies = movieDao.getAll();

        MovieChart movieChart = new MovieChart(this, movies);
        setContentView(movieChart);
    }

    class MyOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"Movie saved!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLifecycle().addObserver(new MyObserver());

        initializeControls();
        movie = new Movie();

        btnSave.setOnClickListener(this);
        btnSave.setOnClickListener(new MyOnClickListener());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Movie saved!", Toast.LENGTH_LONG).show();
            }
        });
        btnSave.setOnClickListener(view ->
        {
            movie.setTitle(etTitle.getText().toString());
            Log.d(TAG, movie.toString());
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            intent.putExtra("movie",movie);
            startActivity(intent);
        });
    }

    private void initializeControls() {
        btnSave = findViewById(R.id.btnSave);
        etTitle = findViewById(R.id.etTitle);
        spGenre = findViewById(R.id.spGenre);
        spGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String genre = adapterView.getItemAtPosition(i).toString();
                movie.setGenre(Genre.valueOf(genre));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sbDuration = findViewById(R.id.sbDuration);
        sbDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                movie.setDuration(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btnRelease = findViewById(R.id.btnRelease);
        btnRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        c.set(Calendar.YEAR, year);
                        c.set(Calendar.MONTH, month);
                        c.set(Calendar.DAY_OF_MONTH, day);
                        Date release =c.getTime();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd YYYY");
                        movie.setRelease(release);
                        btnRelease.setText(dateFormat.format(release));
                    }}, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this,"Movie saved!", Toast.LENGTH_LONG).show();
    }
}