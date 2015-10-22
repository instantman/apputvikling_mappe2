package s180859_s198527.mappe2;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import java.util.Calendar;

public class Settings extends AppCompatActivity implements OnClickListener {

    private Button btnSave, btnStartService, btnStopService, btnTimupikku;
    private EditText editSMSText, editSMSTime;

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
    }

    public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
        }

        public void showTimePickerDialog(View v) {
            DialogFragment newFragment = new TimePickerFragment();
            newFragment.show(getSupportFragmentManager(), "timePicker");
        }
    }

    public void setListener() {
        btnTimupikku = (Button)findViewById(R.id.timupikku);
        btnTimupikku.setOnClickListener(this);
        btnSave = (Button)findViewById(R.id.button_save);
        btnSave.setOnClickListener(this);
        btnStartService = (Button)findViewById(R.id.button_startService);
        btnStartService.setOnClickListener(this);
        btnStopService = (Button)findViewById(R.id.button_stopService);
        btnStopService.setOnClickListener(this);

    }

    public void setSMSPreferences() {
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
        editor.apply();

        Log.d("SMSTEXT", smsText);
        Log.d("SMSTIME",smsTime);
    }

    // Håndterer hva som skjer når knapper blir trykket
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.timupikku:

                break;
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
        }
    }
}

