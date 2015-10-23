/** 
 * This is a background service that uses AlarmManager to start SMSService.
 * Receives start and stop requests from SMSReceiver.
 * Runs service in separate worker-thread until stopped.
*/

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

    Context context; /* Sets the activity context */

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        Log.d("SMSAlarm", "Class created");
    }

    /* Starts the service */
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*Thread thread = new Thread(new AlarmThread(startId));
        thread.run();
        Log.d("SMSAlarm", "Service started");*/

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



        //Calendar cal = Calendar.getInstance();
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
