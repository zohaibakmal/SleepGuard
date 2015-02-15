package dartmouth.edu.sleepguard;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private Switch mSwitch;
    private ImageView mBackgroundView;
    public SleepGuardServiceReceiver mReceiverForTest;
   // int backgroundImageId = getResources().getIdentifier(R.drawable.main_activity_background);
   // int backgroundImageNightId = getResources().getIdentifier("dartmouth.edu.sleepguard:drawable/" + "main_activity_background_night.png", null, null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwitch = (Switch) findViewById(R.id.sleepToogle);
        mBackgroundView = (ImageView) findViewById(R.id.backgroundImage);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mBackgroundView.setImageResource(R.drawable.main_activity_background_night);
                }else{
                    mBackgroundView.setImageResource(R.drawable.main_activity_background);
                }
            }
        });
        setupServiceReceiver();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Starts the IntentService
    public void onStartService(View v) {
        // Construct our Intent specifying the Service
        Intent i = new Intent(this, SleepGuardBackgroundService.class);
        // Add extras to the bundle
        i.putExtra("foo", "bar");
        // Start the service
        startService(i);
    }
    // Setup the callback for when data is received from the service
    public void setupServiceReceiver() {
        mReceiverForTest = new SleepGuardServiceReceiver(new Handler());
        // This is where we specify what happens when data is received from the service
        mReceiverForTest.setReceiver(new SleepGuardServiceReceiver.Receiver() {
            @Override
            public void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode == RESULT_OK) {
                    String resultValue = resultData.getString("resultValue");
                    Toast.makeText(MainActivity.this, resultValue, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
