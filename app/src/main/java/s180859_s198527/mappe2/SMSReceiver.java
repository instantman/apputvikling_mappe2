package s180859_s198527.mappe2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("SMSReceiver", "Broadcast received");
        PowerManager pm = (PowerManager) context.getSystemService(context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "SMS");

        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Log.d("SMSReceiver", "BOOT_COMPLETED received");
            wl.acquire();
            Intent myIntent = new Intent(context, SMSService.class);
            context.startService(myIntent);
            wl.release();
        }
        else if (intent != null) {
            Log.d("SMSReceiver", "Intent received");
            wl.acquire();
            Intent myIntent = new Intent(context, SMSService.class);
            context.startService(myIntent);
            wl.release();
        }
    }
}
