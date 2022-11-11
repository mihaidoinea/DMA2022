package ro.csie.en.dma07;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadThreadTask extends Thread implements Runnable{

    String imageUrl;
    private Handler uiHandler;

    public DownloadThreadTask(String url, Handler uiHandler) {
        this.imageUrl = url;
        this.uiHandler = uiHandler;
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
        Message message = new Message();
        message.what =1122;
        message.obj = bitmap;
        uiHandler.sendMessageAtFrontOfQueue(message);
    }
}
