package ro.csie.en.g1084s04;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
}
