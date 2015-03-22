package pt.ulisboa.tecnico.cmov.logphonecallsapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.EditText;


public class MyPhoneReceiver extends BroadcastReceiver {

    String TAG = MyPhoneReceiver.class.getSimpleName();
    String savedNumber;
    @Override
    public void onReceive(Context context, Intent intent){

        savedNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
        Log.w(TAG, "CALL FROM: " + savedNumber);
    };

}
