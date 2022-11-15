package ro.csie.en.dma07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ExecutorService executorService;
    ImageView ivFirst, ivSecond, ivThird;
    Button btnFirst, btnSecond, btnThird;
    String url = "https://www.urban.ro/wp-content/uploads/2020/09/enolaholmesposter.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeControls();

        executorService = Executors.newFixedThreadPool(3);
    }

    private void initializeControls() {
        ivFirst = findViewById(R.id.ivFirst);
        ivSecond = findViewById(R.id.ivSecond);
        ivThird = findViewById(R.id.ivThird);
        btnFirst = findViewById(R.id.btnFirst);

        btnFirst.setOnClickListener(view -> {
            Future<Bitmap> result = executorService.submit(new DownloadCallableTask(url));
            try {
                ivFirst.setImageBitmap(result.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        btnSecond = findViewById(R.id.btnSecond);
        btnSecond.setOnClickListener(view -> {
            Thread thread = new Thread(new DownloadRunnableTask(MainActivity.this,url, ivSecond));
            thread.start();
        });

        btnThird = findViewById(R.id.btnThird);
        btnThird.setOnClickListener(view -> {
            Handler uiHandler = new Handler(getMainLooper(), new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message message) {
                    int receivedBitmapCode = 1122;
                    if(message.what == receivedBitmapCode)
                    {
                        ivThird.setImageBitmap((Bitmap) message.obj);
                        return  true;
                    }
                    else
                        return false;
                }
            });
            DownloadThreadTask downloadThreadTask = new DownloadThreadTask(url, uiHandler);
            downloadThreadTask.start();
        });
    }
    @Override
    protected void onPause() {
        executorService.shutdown();
        super.onPause();
    }
}