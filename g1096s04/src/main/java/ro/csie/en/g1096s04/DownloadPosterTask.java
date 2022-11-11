package ro.csie.en.g1096s04;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadPosterTask extends Thread implements Runnable  {

    private String imageUrl;
    private Handler handler;

    @Override
    public void run() {
        super.run();
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
        message.what = 1122;
        message.obj = bitmap;
        handler.sendMessageAtFrontOfQueue(message);
    }

    public DownloadPosterTask(String posterUrl, Handler uiHandler) {
        this.imageUrl = posterUrl;
        this.handler = uiHandler;
    }
}
