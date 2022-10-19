package ro.csie.en.g1084s04;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public class MyObservable implements LifecycleEventObserver {
    private String TAG = "LifeCycleEvent";

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {

        switch (event)
        {
            case ON_START:
                Log.d(TAG, "onStart()");
                break;
            case ON_STOP:
                Log.d(TAG, "onStop()");
                break;
            case ON_PAUSE:
                Log.d(TAG, "onPause()");
                break;
            case ON_RESUME:
                Log.d(TAG, "onResume()");
                break;
            case ON_CREATE:
                Log.d(TAG, "onCreate()");
                break;
            case ON_DESTROY:
                Log.d(TAG, "onDestroy()");
                break;
        }
    }
}
