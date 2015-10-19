package s180859_s198527.mappe2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class SMSService extends Service {

    final class SMSThread implements Runnable {
        int service_id;
        SMSThread(int service_id) {
            this.service_id = service_id;
        }

        @Override
        public void run() {


            stopSelf(service_id);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service started...",Toast.LENGTH_LONG).show();
        Thread thread = new Thread(new SMSThread(startId));
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Service destroyed...",Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
