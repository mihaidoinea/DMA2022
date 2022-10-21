package ro.csie.en.dma04;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random = new Random();
    }
    public void btnAddTv(View view)
    {
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        ViewGroup container = (ViewGroup) findViewById(R.id.container);
        MyTextView tv = new MyTextView(this, "New color", color);
        container.addView(tv);
    }
}