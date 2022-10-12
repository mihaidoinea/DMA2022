package ro.csie.en.g1083s03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String param1 = intent.getStringExtra("param1");
        Toast.makeText(this,"Value: " + param1, Toast.LENGTH_LONG).show();

    }

    public void btnClick(View view)
    {
        if(view.getId() == R.id.button2)
        {
            Intent response = new Intent();
            response.putExtra("response","Hello back from SecondActivity!");
            setResult(RESULT_OK, response);
        }
        else
        {
            setResult(RESULT_CANCELED);
        }
        finish();
    }
}