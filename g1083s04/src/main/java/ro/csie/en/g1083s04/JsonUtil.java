package ro.csie.en.g1083s04;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JsonUtil {

    public static String getJsonFromResource(Context context, int resId) {
        String result = null;
        try(InputStream is = context.getResources().openRawResource(resId))
        {
            result = new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .parallel()
                    .collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Movie> readJson(String jsonMovieArray) {
        ArrayList<Movie> result = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonMovieArray);
            result = new ArrayList<>();
            for(int index=0; index<jsonArray.length(); index++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                Movie movie = readJsonObject(jsonObject);
                result.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Movie readJsonObject(JSONObject jsonObject) {
        jsonObject
    }
}
