package ro.csie.en.g1096s03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            String result1 = data.getStringExtra("result");
                            Toast.makeText(getApplicationContext(), "Value:" + result1,
                                    Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(),
                                    "SecondActivity was cancelled!", Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void btnClick(View view)
    {
        Intent intent= new Intent(this, SecondActivity.class);
        intent.putExtra("param1", "Hello from MainActivity!");
        //startActivity(intent);
        launcher.launch(intent);
    }
}