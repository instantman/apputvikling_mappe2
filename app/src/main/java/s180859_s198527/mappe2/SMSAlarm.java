package s180859_s198527.mappe2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import java.util.Calendar;

public class SMSAlarm extends Service {

    Context context = this;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("SMSAlarm", "Class created");
    }

    // Starts the service
    public int onStartCommand(Intent intent, int flags, int startId) {
        Thread thread = new Thread(new AlarmThread(startId));
        thread.run();
        Log.d("SMSAlarm", "Service started");
        return START_STICKY;
    }

    // Stops the service
    @Override
    public void onDestroy() {
        Log.d("SMSAlarm", "Service destroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // Runs the service in separate thread to avoid locking UI-thread
    final class AlarmThread implements Runnable {
        int service_id;
        AlarmThread(int service_id) {
            this.service_id = service_id;
        }
        @Override
        public void run() {
            Log.d("SMSAlarm", "Thread started");
            AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
            Calendar cal = Calendar.getInstance();
            //set the alarms to start in the time period
            cal.add(Calendar.MILLISECOND,60000);
            Intent i = new Intent(context, SMSReceiver.class);
            PendingIntent getSqlUpdatesTimer = PendingIntent.getBroadcast(context, 0, i, 0);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 7200000, getSqlUpdatesTimer);
        }
    }
}
