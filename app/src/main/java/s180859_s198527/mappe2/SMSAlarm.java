package s180859_s198527.mappe2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
            SharedPreferences shared = getSharedPreferences("SMSPrefs", MODE_PRIVATE);
            String smsTime = shared.getString("timeKey", "null");
            Log.d("PREFS",smsTime);
            SimpleDateFormat f = new SimpleDateFormat("hh:mm");
            long millis = 600000;
            try {
                Date d = f.parse(smsTime);
                Log.d("FORMATTED",""+d);
                millis = d.getTime();
                Log.d("IN MILLIS",""+millis);
            }catch (ParseException pe) {
                Log.d("ParseException",""+pe);
            }

            AlarmManager alarmManager = (AlarmManager)context.getSystemService(ALARM_SERVICE);
            // Alarm fires once 60 seconds after start
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MILLISECOND,30000);
            PendingIntent alarmIntent;
            Intent intent = new Intent(context, SMSReceiver.class);
            alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            //alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + 60 * 1000, alarmIntent);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 30000, alarmIntent);
        }
    }
}
