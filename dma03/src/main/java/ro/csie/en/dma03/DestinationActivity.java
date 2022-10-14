package ro.csie.en.dma03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DestinationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        TextView values = (TextView)findViewById(R.id.txtValues);

        // extract any data passed by the caller
        Intent callingIntent = getIntent();
        if (callingIntent != null) {
            String str = callingIntent.getStringExtra("StringData");
            Integer int1 = callingIntent.getIntExtra("IntData", -1);

            Toast.makeText(this, "Value:" + str, Toast.LENGTH_LONG).show();
        }
    }
}