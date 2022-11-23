package ro.csie.en.dma08;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpConnectionService {

    private static final String TAG = HttpConnectionService.class.getName();
    private String recipeJsonUrl;

    public HttpConnectionService(String recipeJson) {

        this.recipeJsonUrl = recipeJson;
    }

    public String getData() {
        StringBuilder jsonFile = new StringBuilder();
        HttpURLConnection connection = null;
        try {
            URL url = new URL(recipeJsonUrl);
            connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonFile.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        Log.d(TAG, "Stop:"+jsonFile.toString());
        return jsonFile.toString();
    }

    public String postData(String jsonArray)
    {
        return null;
    }
}

