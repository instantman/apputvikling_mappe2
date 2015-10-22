/** 
 * This is an activity used to send SMS-messages based on received input parameters.
 * Uses smsText and phonenumber received from SMSService.
*/

package s180859_s198527.mappe2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;

public class SendSMS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /* Sends SMS-message to received number with received text */
    protected void sendSMSMessage(String text, String number) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, text, null, null);
    }
}
