package dartmouth.edu.sleepguard;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import dartmouth.edu.sleepguard.util.Constants;

/**
 * Created by zohaibakmal on 2/12/2015.
 */
public class SleepGuardBackgroundService extends IntentService {



    public SleepGuardBackgroundService(){
        super("Background-Sleep-Guard-Service");
        Log.v(Constants.SleepGuardBackGroundServiceTag, "In constructor");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Extract the receiver passed into the service
        SleepGuardServiceReceiver rec = intent.getParcelableExtra("receiver");
        // Extract additional values from the bundle
        String val = intent.getStringExtra("foo");
        // To send a message to the Activity, create a pass a Bundle
        Bundle bundle = new Bundle();
        bundle.putString("resultValue", "My Result Value. Passed in: " + val);
        // Here we call send passing a resultCode and the bundle of extras
        rec.send(Activity.RESULT_OK, bundle);
        Log.v(Constants.SleepGuardBackGroundServiceTag, "In onHandleIntent");
    }
}
