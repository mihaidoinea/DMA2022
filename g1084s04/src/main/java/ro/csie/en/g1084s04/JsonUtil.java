package ro.csie.en.g1084s04;

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

    public static String getJsonFromResource(Context context, int resId)
    {
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

    public static ArrayList<Movie> getFromJson(String jsonArrayString) {
        ArrayList<Movie> results = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            for(int index = 0; index<jsonArray.length(); index++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                Movie movie = readMovieFromJson(jsonObject);
                results.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return results;
    }

    private static Movie readMovieFromJson(JSONObject jsonObject) {

    }
}
