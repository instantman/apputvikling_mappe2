package s180859_s198527.mappe2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendSMS extends AppCompatActivity implements OnClickListener {

    private EditText testMessage, testNumber;
    private Button testSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendsms);

        testMessage = (EditText)findViewById(R.id.editTextMessage);
        testNumber = (EditText)findViewById(R.id.editTextPhoneNo);
        testSMS = (Button)findViewById(R.id.button_sendSMS);
        testSMS.setOnClickListener(this);
    }

    protected void sendSMSMessage(String text, String number) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, text, null, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sendSMS:
                String testMsg = testMessage.getText().toString();
                String testNr = testNumber.getText().toString();
                sendSMSMessage(testMsg, testNr);
                break;
        }
    }
}
