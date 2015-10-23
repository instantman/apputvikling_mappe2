/** 
 * This is a background service that uses AlarmManager to start SMSService.
 * Receives start and stop requests from SMSReceiver.
 * Runs service in separate worker-thread until stopped.
*/

package s180859_s198527.mappe2;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Notification.Builder;
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

    Context context; /* Sets the activity context */

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        Log.d("SMSAlarm", "Class created");
    }

    /* Starts the service */
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Notifications
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent nintent = new Intent(this, Settings.class);
        PendingIntent pnintent = PendingIntent.getActivity(this, 0, nintent, 0);
        Notification noti = new Notification.Builder(this)
                .setContentTitle("TITTEL")
                .setContentText("TEXT")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pnintent).build();

        noti.flags |= Notification.FLAG_ONGOING_EVENT;
        notificationManager.notify(0, noti);

        // Get smsTime from SharedPreferences
        SharedPreferences shared = getSharedPreferences("SMSPrefs", MODE_PRIVATE);
        String smsTime = shared.getString("timeKey", "null");
        Log.d("SAVED TIME",smsTime);
        // Format smsTime by spliting string
        String[] time = smsTime.split(":");
        String newHr = time[0];
        String newMin = time[1];

        long alarma;
        Calendar calTarget = Calendar.getInstance();
        Calendar calNow = Calendar.getInstance();
        calTarget.setTimeInMillis(System.currentTimeMillis());
        calTarget.set(Calendar.SECOND, 0);
        calTarget.set(Calendar.HOUR_OF_DAY, Integer.parseInt(newHr));
        calTarget.set(Calendar.MINUTE, Integer.parseInt(newMin));
        if(calTarget.getTimeInMillis()<=calNow.getTimeInMillis()){
           alarma = calTarget.getTimeInMillis() + (AlarmManager.INTERVAL_DAY+1);
        }
        else{
            alarma = calTarget.getTimeInMillis();
        }
        Log.d("TID2: ", "" + calTarget.getTime());

        //AlarmManager
        Intent i = new Intent(context, SMSService.class);
        PendingIntent pintent = PendingIntent.getService(context, 0, i, 0);
        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, alarma, AlarmManager.INTERVAL_DAY, pintent); //type, første, repeat interval, pintent
        Log.d("SMSAlarm", "Alarm SET: " +" - - "+alarma);
        return super.onStartCommand(intent, flags, startId);
    }

    /* Stops the service */
    @Override
    public void onDestroy() {
        Intent i = new Intent(context, SMSService.class);
        PendingIntent pintent = PendingIntent.getService(context, 0, i, 0);
        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pintent);
        Log.d("SMSAlarm", "Service destroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
