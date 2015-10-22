package s180859_s198527.mappe2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SMSService extends Service {

    Context context = this; // Set context for DBHandler in Thread

    @Override
    public void onCreate() {
        super.onCreate();
    }

    // Starts the service
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service started...",Toast.LENGTH_LONG).show();
        Thread thread = new Thread(new SMSThread(startId));
        thread.run();
        Log.d("SMSService","Service started");
        return START_STICKY;
    }

    // Stops the service
    @Override
    public void onDestroy() {
        Toast.makeText(this,"Service destroyed...",Toast.LENGTH_LONG).show();
        Log.d("SMSService", "Service destroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // Runs the service in separate thread to avoid locking UI-thread
    final class SMSThread implements Runnable {
        int service_id;
        SMSThread(int service_id) {
            this.service_id = service_id;
        }

        @Override
        public void run() {
            Log.d("SMSService", "Thread started");
            // Get current device date
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 0);
            Date date = calendar.getTime();
            String fDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            // Sends SMS to contacts with birthday matching current date
            DBHandler d = new DBHandler(context);
            List<Contact> c = d.getAllContacts();
            SendSMS smsSender = new SendSMS();
            SharedPreferences shared = getSharedPreferences("SMSPrefs",MODE_PRIVATE);
            String smsText = shared.getString("textKey", "null");
            for(Contact cont : c ){
                if(cont.getBirthdate().equals(fDate)) {
                    try {
                        smsSender.sendSMSMessage("Hei "+cont.getSurname() + " "
                                + cont.getLastname()+"! "+smsText, cont.getPhoneNr());
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            Log.d("SMSService", "Thread stopped");
            stopSelf(service_id); // Service stops itself on complete
        }
    }
}
