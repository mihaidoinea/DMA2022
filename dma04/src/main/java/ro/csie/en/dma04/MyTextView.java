package ro.csie.en.dma04;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatTextView;

public class MyTextView extends AppCompatTextView {

    public MyTextView(Context context, final String colorName,
                      int colorId) {
        super(context);

        int paddingSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 20,
                context.getResources().getDisplayMetrics()
        );

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, paddingSize, 0, 0);
        setLayoutParams(lp);

        setPadding(0, paddingSize, 0, paddingSize);

        setBackgroundColor(colorId);

        setText(colorName);

        setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        setTypeface(getTypeface(), Typeface.BOLD);
        setTextAlignment(TEXT_ALIGNMENT_CENTER);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),
                        String.format("This box is %s", colorName),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}

