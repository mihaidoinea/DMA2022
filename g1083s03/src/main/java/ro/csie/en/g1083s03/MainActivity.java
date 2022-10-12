package ro.csie.en.g1083s03;

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

    ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        //result as response from secondActivity
                        int resultCode = result.getResultCode();
                        if(resultCode == RESULT_OK)
                        {
                            Intent data = result.getData();
                            String response = data.getStringExtra("response");
                            Toast.makeText(getApplicationContext(), "Value: " + response,Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "SecondActivity was cancelled!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void btnClick(View view)
    {
        Intent intent = new Intent(this, SecondActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("param1","Hello from MainActivity!");
        intent.putExtras(bundle);

//        intent.putExtra("param1", "Hello from MainActivity!");
        //start normal activity mode
        //startActivity(intent);
        //start activity for result mode
        activityResultLauncher.launch(intent);
    }
}