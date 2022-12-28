package ro.csie.en.g1096s04;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MovieChart extends View {

    private Map<String, Integer> stats;
    private Context context;
    private Random random;
    private Paint paint;

    public MovieChart(Context context, List<Movie> allMovies) {
        super(context);
        this.context = context;
        this.random = new Random();
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        stats = crunchStats(allMovies);
    }

    private Map<String, Integer> crunchStats(List<Movie> allMovies) {
        Map<String, Integer> stats = new HashMap<>();
        for(Movie movie: allMovies)
        {
            if(stats.containsKey(movie.getGenre().toString()))
            {
                Integer value = stats.get(movie.getGenre().toString());
                stats.put(movie.getGenre().toString(), value+1);
            }
            else
                stats.put(movie.getGenre().toString(), 1);
        }
        return stats;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int maxValue = getMaxValue();
        if(stats.size() > 0) {
            float colWidth = getWidth() / stats.size();
            drawValues(canvas, maxValue, colWidth);
        }
    }

    private void drawValues(Canvas canvas, int maxValue, float colWidth) {
        int currColumn = 0;
        for(String genre:stats.keySet())
        {
            int color = generateColor();
            int value = stats.get(genre);
            paint.setColor(color);
            drawColumn(canvas, maxValue, colWidth, currColumn, value);
            drawLabel(canvas, colWidth, currColumn, genre, value);
            currColumn++;
        }
    }

    private void drawLabel(Canvas canvas, float colWidth, int currColumn, String genre, int value) {
        paint.setColor(Color.BLACK);
        paint.setTextSize((float) (0.4 * colWidth));
        float x = (float)((currColumn +  0.5) * colWidth);
        float y = (float)(0.9 * getHeight());
        canvas.rotate(270, x,y);
        canvas.drawText(genre +":" + value,x,y, paint);
        canvas.rotate(-270, x, y);
    }

    private void drawColumn(Canvas canvas, int maxValue, float colWidth, int currColumn, int value) {
        float x1 = currColumn * colWidth;
        float y1 = (1 - (float)value/maxValue) * getHeight();
        float x2 = (currColumn + 1) * colWidth;
        float y2 = getHeight();
        canvas.drawRect(x1,y1,x2, y2, paint);
    }

    private int generateColor() {
        return Color.argb(255,
                random.nextInt(255),
                random.nextInt(255),
                random.nextInt(255));
    }

    private int getMaxValue() {
        int max = 0;
        for(Integer value: stats.values())
        {
            max = max<value ? value: max;
        }
        return max;
    }
}
