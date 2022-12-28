package ro.csie.en.g1084s04;

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
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave;
    private EditText etTitle, etDate;
    private Spinner spGenre;
    private SeekBar sbDuration;
    Movie movie = null;
    private String TAG = MainActivity.class.getSimpleName();

    public void parseJson(View view) {
        String result = JsonUtil.getJsonFromResource(this,R.raw.movies);
        Log.d(TAG, result);
        ArrayList<Movie> movieList = JsonUtil.getFromJson(result);
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        intent.putParcelableArrayListExtra("movies", movieList);
        startActivity(intent);
    }

    public void viewChart(View view) {
        DatabaseManager dbManager = DatabaseManager.getInstance(this);
        MovieDao movieDao = dbManager.getMovieDao();

        List<Movie> all = movieDao.getAllMovies();

        MovieChart movieChart = new MovieChart(this, all);
        setContentView(movieChart);
    }

    class MyOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"Hello from btnSave",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getLifecycle().addObserver(new MyObservable());



        movie = new Movie();
        initializeControls();


        btnSave.setOnClickListener(this);
        btnSave.setOnClickListener(new MyOnClickListener());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Hello from btnSave",
                        Toast.LENGTH_LONG).show();
            }
        });
        btnSave.setOnClickListener(view ->
        {
            movie.setTitle(etTitle.getText().toString());
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
                //genre = ((TextView)view).toString();
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
        etDate = findViewById(R.id.etDate);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        c.set(Calendar.YEAR, year);
                        c.set(Calendar.MONTH, month);
                        c.set(Calendar.DAY_OF_MONTH, day);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
                        Date release = c.getTime();
                        movie.setRelease(release);
                        etDate.setText(dateFormat.format(release));
                    }
                }, year, month, day).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this,"Hello from btnSave",
                Toast.LENGTH_LONG).show();
    }
}