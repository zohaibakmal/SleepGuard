package dartmouth.edu.sleepguard;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;



/**
 * Created by zohaibakmal on 2/12/2015.
 */
public class SleepGuardServiceReceiver extends ResultReceiver {

    private Receiver mReceiver;

    // Constructor takes a handler
    public SleepGuardServiceReceiver (Handler handler){
        super(handler);
    }

    // Setter for assigning the receiver
    public void setReceiver(Receiver receiver) {
        this.mReceiver = receiver;
    }

    // Defines our event interface for communication
    public interface Receiver {
        public void onReceiveResult(int resultCode, Bundle resultData);
    }

    // Delegate method which passes the result to the receiver if the receiver has been assigned
    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }
}
