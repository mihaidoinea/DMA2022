package ro.csie.en.g1096s03;

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
        Bundle extras = intent.getExtras();
        String param1 = extras.getString("param1");
        Toast.makeText(this,"Value:" + param1, Toast.LENGTH_LONG).show();
    }

    public void btnBack(View view)
    {
        Intent intent = new Intent();
        intent.putExtra("result","Hello back from SecondActivity!");
        setResult(RESULT_OK, intent);
        finish();
    }
}