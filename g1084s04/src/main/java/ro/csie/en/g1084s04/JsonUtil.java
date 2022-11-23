package ro.csie.en.g1084s04;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        ArrayList<Movie> results = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            for(int index = 0; index<jsonArray.length(); index++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                Movie movie = readMovieFromJson(jsonObject);
                results.add(movie);
            }

        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return results;
    }

    private static Movie readMovieFromJson(JSONObject jsonObject) throws JSONException, ParseException {
        String movieTitle = jsonObject.getString("title");
        String genre = jsonObject.getString("genre");
        Genre movieGenre = Genre.valueOf(genre);
        String release = jsonObject.getString("release");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date movieRelease = simpleDateFormat.parse(release);
        int movieDuration = jsonObject.getInt("duration");
        return new Movie(movieTitle, movieGenre, movieRelease, movieDuration);
    }
}
