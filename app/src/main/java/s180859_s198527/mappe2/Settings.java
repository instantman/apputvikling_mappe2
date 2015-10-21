package s180859_s198527.mappe2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class Settings extends AppCompatActivity implements OnClickListener {

    private Button btnSave, btnStartService, btnStopService, btnTestSms;
    private EditText editSMSText, editSMSTime, editSMSNo;

    /** Testkode for å hente SMSText herfra til SendSMS */
    // Initialiserer
    public static final String SMSPreferences = "SMSPrefs";
    public static final String SMSText = "textKey";
    public static final String SMSTime = "timeKey";
    SharedPreferences sharedpreferences;

    Context context = this; // Set context for switch-case

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Oppknapp i ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_previous);

        setListener();
        setSMSPreferences();
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

    public void setSMSPreferences() {
        /** Testkode for å hente SMSText herfra til SendSMS */
        // Get smsText and smsTime from EditText
        editSMSText = (EditText)findViewById(R.id.textfield_SMSText);
        editSMSTime = (EditText)findViewById(R.id.textfield_SMSTime);
        String smsText = editSMSText.getText().toString();
        String smsTime = editSMSTime.getText().toString();

        // Add smsText and smsTime to shared preferences
        sharedpreferences = getSharedPreferences(SMSPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SMSText, smsText);
        editor.putString(SMSTime, smsTime);
        editor.commit();

        Log.d("SMSTEXT", smsText);
        Log.d("SMSTIME",smsTime);
    }

    // Håndterer hva som skjer når knapper blir trykket
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_save:
                setSMSPreferences();
                Log.d("Settings","Text n Time saved");
                break;
            case R.id.button_startService:
                Intent i2 = new Intent(context,SMSService.class);
                startService(i2);
                break;
            case R.id.button_stopService:
                Intent i3 = new Intent(context,SMSService.class);
                stopService(i3);
                break;
            case R.id.button_testSms:
                Intent i1 = new Intent(this,SendSMS.class);
                startActivity(i1);
                break;
        }
    }
}

