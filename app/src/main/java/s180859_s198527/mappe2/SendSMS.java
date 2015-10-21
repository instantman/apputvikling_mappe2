package s180859_s198527.mappe2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;

public class SendSMS extends AppCompatActivity {

    private String smsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("SendSMS","onCreate");
    }

    public String getText() {
        SharedPreferences shared = getSharedPreferences("SMSPrefs",MODE_PRIVATE);
        smsText = shared.getString("textKey","null");
        Log.d("text",""+smsText);
        return smsText;
    }

    protected void sendSMSMessage(String text, String number) {
            Log.d("sendSMS","Sender SMS");
            String message = "Hei "+text+"! "+getText();
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);
    }
}
