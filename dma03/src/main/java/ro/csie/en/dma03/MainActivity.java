package ro.csie.en.dma03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the UI controls
        findViewById(R.id.create_explicit).setOnClickListener(this);
        findViewById(R.id.create_implicit).setOnClickListener(this);
        findViewById(R.id.btnMediaIntents).setOnClickListener(this);
        findViewById(R.id.btnAppIntents).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Figure out which button was clicked
        int viewClicked = v.getId();

        if (viewClicked == R.id.create_explicit) {
            // TODO: Build an explicit Intent to launch our Activity
            Intent i = new Intent(this, DestinationActivity.class);

            // TODO: send data along with the Intent to the destination
            i.putExtra("IntData", 1234);
            i.putExtra("StringData", "Message form MainActivity!");

            // Start the activity with our explicit intent
            startActivity(i);
        }
        else if (viewClicked == R.id.create_implicit) {
            // TODO: Build an implicit intent to handle a type of action
            String textMessage = "This is a sample message";
            Intent i = new Intent();
            i.setAction(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_TEXT, textMessage);

            // TODO: use an intent chooser to force a choose dialog
            Intent chooser = Intent.createChooser(i, "Select an app:");

            // Verify that the intent will resolve to an activity

            startActivity(chooser);

            // Typically you would handle the null case here by informing the user
            // that there is no installed app to handle this intent or
            // by taking some other action
        }

        // Handle button clicks to start the other intent examples
        if (viewClicked == R.id.btnAppIntents) {
            Intent i = new Intent(this, AppsActivity.class);
            startActivity(i);
        }
        else if (viewClicked == R.id.btnMediaIntents) {
            Intent i = new Intent(this, MediaActivity.class);
            startActivity(i);
        }
    }
}
