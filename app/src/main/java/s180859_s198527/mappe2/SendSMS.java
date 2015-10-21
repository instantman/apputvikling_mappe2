package s180859_s198527.mappe2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;

public class SendSMS extends AppCompatActivity {

    private String smsText, smsTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("SendSMS","onCreate");
    }

    protected void sendSMSMessage(String text, String number) {
            SharedPreferences shared = getSharedPreferences("SMSPrefs",MODE_PRIVATE);
            smsText = shared.getString("textKey","null");
            Log.d("text",""+smsText);
            smsTime = shared.getString("timeKey","null");
            Log.d("time",""+smsTime);
            String message = "Hei "+text+"! "+smsText;
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);
    }
}
