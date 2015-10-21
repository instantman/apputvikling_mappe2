package s180859_s198527.mappe2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity implements OnClickListener {

    private Button btnSave, btnStartService, btnStopService, btnTestSms;
    private EditText editSMSText, editSMSTime, editSMSNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Oppknapp i ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_previous);

        setTextField();
        setListener();
    }

    public void setTextField() {
        editSMSText = (EditText)findViewById(R.id.textfield_SMSText);
        editSMSTime = (EditText)findViewById(R.id.textfield_SMSTime);
    }

    public void setListener() {
        btnSave = (Button)findViewById(R.id.button_save);
        btnSave.setOnClickListener(this);
        btnStartService = (Button)findViewById(R.id.button_startService);
        btnStartService.setOnClickListener(this);
        btnStopService = (Button)findViewById(R.id.button_stopService);
        btnStopService.setOnClickListener(this);
        btnTestSms = (Button)findViewById(R.id.button_testSms);
        btnTestSms.setOnClickListener(this);
    }

    public void startService(View view) {
        Intent intent = new Intent(this,SMSService.class);
        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this,SMSService.class);
        stopService(intent);
    }

    // Håndterer hva som skjer når knapper blir trykket
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_save:
                // For å lagre tekst og tidspunkt for SMS
                break;
            case R.id.button_startService:

                break;
            case R.id.button_stopService:

                break;
            case R.id.button_testSms:
                Intent i1 = new Intent(this,SendSMS.class);
                startActivity(i1);
                break;
        }
    }
}

