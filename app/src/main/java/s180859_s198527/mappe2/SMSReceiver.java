package s180859_s198527.mappe2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class SMSReceiver extends WakefulBroadcastReceiver {
    // WakefulBroadcastReceiver hindrer enhten fra å gå i sleep mens service kjøres
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent startServiceIntent = new Intent(context, SMSService.class);
            context.startService(startServiceIntent);
        }
    }
}
