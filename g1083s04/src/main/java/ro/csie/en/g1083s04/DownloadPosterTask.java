package ro.csie.en.g1083s04;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

public class DownloadPosterTask implements Callable<Bitmap> {

    private String imageUrl;

    public Bitmap getImage() {
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

        return bitmap;
    }

    public DownloadPosterTask(String posterUrl) {
        this.imageUrl = posterUrl;
    }

    @Override
    public Bitmap call() throws Exception {
        //return bitmap
        return getImage();
    }
}
