package ro.csie.en.g1083s04;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MovieChart extends View {

    private Paint paint;
    private Random random;
    private HashMap<String, Integer> stats;


    public MovieChart(Context context, List<Movie> movies) {
        super(context);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        random = new Random();
        stats = crunchStats(movies);
    }

    private HashMap<String, Integer> crunchStats(List<Movie> movies) {
        return null;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int maxValue = getMaxValue();
        float colWidth = getWidth() / stats.keySet().size();
        drawValues(canvas, maxValue, colWidth);
    }

    private void drawValues(Canvas canvas, int maxValue, float colWidth) {
        int currColumn = 0;
        for(String key: stats.keySet())
        {
            int value = stats.get(key);
            drawColumn(canvas, value, maxValue, colWidth, key, currColumn);
            currColumn++;
        }
    }

    private void drawColumn(Canvas canvas, int value, int maxValue, float colWidth, String key, int currColumn) {
        int color = generateColor();
        paint.setColor(color);
        float x1 = currColumn * colWidth;
        float y1 = (1- (float)value/maxValue) * getHeight();
        float x2 = (currColumn + 1) * colWidth;
        float y2 = getHeight();
        canvas.drawRect(x1, y1, x2, y2, paint);
    }

    private int generateColor() {
        return Color.argb(100,
                random.nextInt(255),
                random.nextInt(255),
                random.nextInt(255));
    }

    private int getMaxValue() {
        int max = 0;
        for(String genre : stats.keySet())
        {
            int value = stats.get(genre);
            max = max < value ? value: max;
        }
        return max;
    }
}
