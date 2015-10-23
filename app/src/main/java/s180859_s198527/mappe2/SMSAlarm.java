/** 
 * This is a background service that uses AlarmManager to start SMSService.
 * Receives start and stop requests from SMSReceiver.
 * Runs service in separate worker-thread until stopped.
*/

package s180859_s198527.mappe2;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Calendar;

public class SMSAlarm extends Service {

    Context context; /* Sets the activity context */

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    /* Starts the service */
    public int onStartCommand(Intent intent, int flags, int startId) {
        /* Get smsTime from SharedPreferences */
        SharedPreferences shared = getSharedPreferences("SMSPrefs", MODE_PRIVATE);
        String smsTime = shared.getString("timeKey", "null");

        /* Shows notification when service is running */
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent nintent = new Intent();
        PendingIntent pnintent = PendingIntent.getActivity(this, 0, nintent, 0);
        Notification noti = new Notification.Builder(this)
                .setContentTitle("SMSAlarm")
                .setContentText("Alarm is set to "+smsTime+".")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pnintent).build();
        noti.flags |= Notification.FLAG_ONGOING_EVENT;
        notificationManager.notify(1, noti); // Shows notification

        /* Format smsTime by splitting string on : */
        String[] time = smsTime.split(":");
        String newHr = time[0];
        String newMin = time[1];
        long alarmTime;

        Calendar calTarget = Calendar.getInstance();
        Calendar calNow = Calendar.getInstance();
        calTarget.setTimeInMillis(System.currentTimeMillis());
        calTarget.set(Calendar.SECOND, 0);
        calTarget.set(Calendar.HOUR_OF_DAY, Integer.parseInt(newHr));
        calTarget.set(Calendar.MINUTE, Integer.parseInt(newMin));
        if(calTarget.getTimeInMillis()<=calNow.getTimeInMillis()){
           alarmTime = calTarget.getTimeInMillis() + (AlarmManager.INTERVAL_DAY+1);
        }
        else{
            alarmTime = calTarget.getTimeInMillis();
        }

        /* Creates new alarm */
        Intent i = new Intent(context, SMSService.class);
        PendingIntent pintent = PendingIntent.getService(context, 0, i, 0);
        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        // Triggers at predefined time, then repeats every 24 hours
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, alarmTime, AlarmManager.INTERVAL_DAY, pintent);
        Toast.makeText(this, "Alarm set.", Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    /* Stops the service */
    @Override
    public void onDestroy() {
        // Destroys alarm
        Intent i = new Intent(context, SMSService.class);
        PendingIntent pintent = PendingIntent.getService(context, 0, i, 0);
        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pintent);
        // Destroys notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(1);
        Toast.makeText(this, "Alarm canceled.", Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
