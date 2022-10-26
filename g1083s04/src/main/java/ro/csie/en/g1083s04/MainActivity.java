package ro.csie.en.g1083s04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave, btnRelease;
    private EditText etTitle;
    private Spinner spGenre;
    private SeekBar sbDuration;
    private Movie movie;

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
            Toast.makeText(getApplicationContext(),"Movie: " + movie, Toast.LENGTH_LONG).show();
        });
    }

    private void initializeControls() {
        btnSave = findViewById(R.id.btnSave);
        etTitle = findViewById(R.id.etTitle);
        spGenre = findViewById(R.id.spGenre);
        sbDuration = findViewById(R.id.sbDuration);
        btnRelease = findViewById(R.id.btnRelease);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this,"Movie saved!", Toast.LENGTH_LONG).show();
    }
}