package com.example.s1084s03;

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
        String action = intent.getAction();
        if(action == Intent.ACTION_SEND)
        {
            String s = intent.getClipData().getItemAt(0).toString();
            Toast.makeText(this,"Value: " + s, Toast.LENGTH_LONG).show();
        }
        else {
            String param1 = intent.getStringExtra("param1");
            Toast.makeText(this, "Value: " + param1, Toast.LENGTH_LONG).show();
        }
    }

    public void btClick(View view)
    {
        Intent response = new Intent();
        response.putExtra("result", "Hello back from SecondActivity!");
        setResult(RESULT_OK, response);
        finish();
    }
}