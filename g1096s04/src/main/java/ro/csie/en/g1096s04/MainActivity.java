package ro.csie.en.g1096s04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSave;

    class MyOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.btnSave)
                Toast.makeText(getApplicationContext(), "Movie saved!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeControls();
        getLifecycle().addObserver(new MyObserver());
        btnSave.setOnClickListener(this);
        btnSave.setOnClickListener(new MyOnClickListener());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.btnSave)
                    Toast.makeText(getApplicationContext(), "Movie saved!", Toast.LENGTH_LONG).show();
            }
        });
        btnSave.setOnClickListener(view -> Toast.makeText(getApplicationContext(), "Movie saved!", Toast.LENGTH_LONG).show());

    }

    private void initializeControls() {
        btnSave = findViewById(R.id.btnSave);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnSave)
            Toast.makeText(this, "Movie saved!", Toast.LENGTH_LONG).show();
    }
}