package com.example.s1084s03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> activityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        int resultCode = result.getResultCode();
                        if(resultCode == RESULT_OK) {
                            Intent data = result.getData();
                            String result1 = data.getStringExtra("result");
                            Toast.makeText(getApplicationContext(),
                                    "Response: " + result1,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void btnClick(View view)
    {
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("param1", "Hello from MainActivity!");
//        startActivity(intent);
        activityLauncher.launch(intent);
    }
}