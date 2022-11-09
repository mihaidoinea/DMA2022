package ro.csie.en.dma02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate()");
    }

    public void btnClick(View view)
    {
//        Intent intent  = new Intent(this, SecondActivity.class);
//        startActivity(intent);
        Uri uri = null;
        if(view.getId() == R.id.ivCat)
        {
            uri = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.drawable.cat);
        }
        else if(view.getId() == R.id.ivDog)
        {
            uri = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.drawable.dog);
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/jpeg");
        Intent chooser = Intent.createChooser(intent, "Choose app");
        startActivity(chooser);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}