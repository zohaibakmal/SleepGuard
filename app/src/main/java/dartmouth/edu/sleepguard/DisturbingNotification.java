package dartmouth.edu.sleepguard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by zohaibakmal on 2/16/2015.
 */
public class DisturbingNotification extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.DisturbingNotification)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (Context.NOTIFICATION_SERVICE!=null) {
                            String ns = Context.NOTIFICATION_SERVICE;
                            NotificationManager nMgr = (NotificationManager) getActivity().getSystemService(ns);
                            nMgr.cancel(0);
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (Context.NOTIFICATION_SERVICE!=null) {
                            String ns = Context.NOTIFICATION_SERVICE;
                            NotificationManager nMgr = (NotificationManager) getActivity().getSystemService(ns);
                            nMgr.cancel(0);
                        }
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}