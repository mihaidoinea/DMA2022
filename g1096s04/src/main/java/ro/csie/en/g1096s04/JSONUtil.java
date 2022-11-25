package ro.csie.en.g1096s04;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Movie readMovie(JSONObject jsonObject) throws JSONException {
        String movieTitle = jsonObject.getString("title");

    }
}
