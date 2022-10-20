package ro.csie.en.g1084s04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnSave;

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
        btnSave = findViewById(R.id.btnSave);
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
                Toast.makeText(getApplicationContext(),"Hello from btnSave",
                        Toast.LENGTH_LONG).show());
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this,"Hello from btnSave",
                Toast.LENGTH_LONG).show();
    }
}