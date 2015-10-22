/** 
 * This is a BroadCastReceiver for starting services.
 * Handles start requests from services and activities and BOOT_COMPLETED events.
 * PS! Uses WakeLock. 
*/

package s180859_s198527.mappe2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        /* WakeLock-function to prevent sleep when running SMSService */
        Log.d("SMSReceiver", "Broadcast received");
        PowerManager pm = (PowerManager) context.getSystemService(context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "SMS");
        /* Run SMSSerivce on BOOT_COMPLETED */
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            Log.d("SMSReceiver", "BOOT_COMPLETED received");
            wl.acquire(); // Activate WakeLock
            Intent smsIntent = new Intent(context, SMSService.class);
            context.startService(smsIntent); // Start service
            wl.release(); // Deactivate WakeLock
        }
        /* Run SMSService when receiving any intent */
        else if (intent != null) {
            Log.d("SMSReceiver", "Intent received");
            wl.acquire(); // Activate WakeLock
            Intent myIntent = new Intent(context, SMSService.class);
            context.startService(myIntent); // Start service
            wl.release(); // Deactivate WakeLock
        }
    }
}
