package ro.csie.en.dma07;

import static android.os.Looper.getMainLooper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadRunnableTask implements Runnable {

    String imageUrl;
    MainActivity context;
    ImageView imageView;


    public DownloadRunnableTask(MainActivity context, String url, ImageView ivThird) {
        this.imageUrl = url;
        this.context = context;
        this.imageView = ivThird;
    }

    @Override
    public void run() {
        HttpURLConnection connection = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        Bitmap finalBitmap = bitmap;
//        imageView.setImageBitmap(finalBitmap);
        //1. runOnUiThread
        /*context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(finalBitmap);
            }
        });*/
        //2. handler postback to main thread
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(finalBitmap);
            }
        });
    }
}
