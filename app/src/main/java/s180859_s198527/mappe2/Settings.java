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

        SharedPreferences shared = getSharedPreferences("SMSPrefs",MODE_PRIVATE);
        editSMSText = (EditText)findViewById(R.id.textfield_SMSText);
        btnTimupikku = (Button)findViewById(R.id.timupikku);
        editSMSText.setText(shared.getString("textKey", "null"));
        btnTimupikku.setText(shared.getString("timeKey", "null"));

        setListener();
    }

    public void setListener() {
        btnTimupikku.setOnClickListener(this);
        btnSave = (Button)findViewById(R.id.button_save);
        btnSave.setOnClickListener(this);
        btnStartService = (Button)findViewById(R.id.button_startService);
        btnStartService.setOnClickListener(this);
        btnStopService = (Button)findViewById(R.id.button_stopService);
        btnStopService.setOnClickListener(this);
    }

    public void setSMSPreferences() {
        // Get smsText and smsTime
        String smsText = editSMSText.getText().toString();
        String smsTime = btnTimupikku.getText().toString();

        // Add smsText and smsTime to shared preferences
        sharedpreferences = getSharedPreferences(SMSPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SMSText, smsText);
        editor.putString(SMSTime, smsTime);
        editor.apply();

        Log.d("SMSTEXT", smsText);
        Log.d("SMSTIME", smsTime);
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            TimePickerDialog t = new TimePickerDialog(getActivity(),
                    this, hour, minute, DateFormat.is24HourFormat(getActivity()));
            return t;
        }

        public void onTimeSet(TimePicker view, int setHour, int setMinute) {
            Button b = (Button)getActivity().findViewById(R.id.timupikku);
            String selectedTime = formatTime(setHour,setMinute);
            b.setText(selectedTime);
        }
    }

    public static String formatTime(int hour, int minute) {
        String selectedTime;
        if (hour <= 9 && minute <= 9) {
            selectedTime = "0"+Integer.toString(hour)+":0"+Integer.toString(minute);
            return selectedTime;
        }
        else if (hour <= 9 && minute > 9) {
            selectedTime = "0"+Integer.toString(hour)+":"+Integer.toString(minute);
            return selectedTime;
        }
        else if (hour > 9 && minute <= 9) {
            selectedTime = Integer.toString(hour)+":0"+Integer.toString(minute);
            return selectedTime;
        }
        else {
            selectedTime = Integer.toString(hour)+":"+Integer.toString(minute);
            return selectedTime;
        }
    }

    // Håndterer hva som skjer når knapper blir trykket
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.timupikku:
                TimePickerFragment tf = new TimePickerFragment();
                tf.show(getFragmentManager(), "Time Picker");
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

