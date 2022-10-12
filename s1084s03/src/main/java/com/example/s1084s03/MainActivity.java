package com.example.s1084s03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view)
    {
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("param1", "Hello from MainActivity!");
        startActivity(intent);
    }
}