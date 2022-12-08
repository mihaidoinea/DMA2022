package ro.csie.en.g1096s04;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JSONUtil {

    public static ArrayList<Movie> getJsonFromString(String jsonArrayString) {
        ArrayList<Movie> result = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            for(int index = 0; index <jsonArray.length(); index++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(index);
                Movie movie = readMovie(jsonObject);
                if(result == null)
                    result = new ArrayList<>();
                result.add(movie);
            }
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Movie readMovie(JSONObject jsonObject) throws JSONException, ParseException {
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
