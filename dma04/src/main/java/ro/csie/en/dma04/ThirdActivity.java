package ro.csie.en.dma04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {
    ViewStub viewStub;
    View views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        viewStub = findViewById(R.id.viewStub2);
        views = viewStub.inflate();
    }

    public void btnClick(View view)
    {
        if(view.getId() == R.id.button3)
            viewStub.setVisibility(View.VISIBLE);
        if(view.getId() == R.id.button4) {
            viewStub.setVisibility(View.GONE);

            EditText etPass = views.findViewById(R.id.editTextTextPassword);
            Log.d("ThirdActivity",etPass.getEditableText().toString());
        }

    }
}