package s180859_s198527.mappe2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SMSService extends Service {

    Context context = getApplicationContext();
    // Kjører servicen på en egen thread
    final class SMSThread implements Runnable {
        int service_id;
        SMSThread(int service_id) {
            this.service_id = service_id;
        }

        @Override
        public void run() {
            // Trenger å hente inn valgt tid og tekst for sending (eller skal det gjøres i sendSMS?

            // Henter nåværende dato og tidspunkt
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 0);
            Date date = calendar.getTime();
            String fDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
            String fTime = new SimpleDateFormat("HH:mm").format(date);

            // Sender SMS på valgt tidspunkt hvis noen har bursdag
            DBHandler d = new DBHandler(context);
            List<Contact> c = d.getAllContacts();
            SendSMS smsSender = new SendSMS();
            for(Contact cont : c ){
                if(cont.getBirthdate().equals(fDate) && fTime.equals("12:00")) {
                    try {
                        smsSender.sendSMSMessage(cont.getSurname() + " " + cont.getLastname(), cont.getPhoneNr());
                        Log.d("SMS", "Sent to " + cont.getPhoneNr());
                        Toast.makeText(getApplicationContext(),
                                "Birthday greeting sent to " + cont.getSurname() + " " + cont.getLastname() + ".",
                                Toast.LENGTH_LONG).show();
                    } catch(Exception e) {
                        Log.d("SMS","Not sent :(");
                        Toast.makeText(getApplicationContext(),
                                "Birthday greeting failed. Please check settings.",
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                } else {
                    Log.d("SMS","No burfdaiz :(");
                }
            }
            stopSelf(service_id);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    // Starter servicen
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service started...",Toast.LENGTH_LONG).show();
        Thread thread = new Thread(new SMSThread(startId));
        return START_STICKY;
    }

    // Stopper servicen
    @Override
    public void onDestroy() {
        Toast.makeText(this,"Service destroyed...",Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
