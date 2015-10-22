/** 
 * This is an activity used to edit settings in the application by the user.
 * Handles input of SMSText and SMSTime and saves the data to SharedPreferences.
 * Includes buttons to start and stop SMSService.
 */

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
import java.util.Date;

public class Settings extends AppCompatActivity implements OnClickListener {
    
    Context context = this; /* Sets the activity context */
    
    private Button btnSave, btnStartService, btnStopService, btnTimupikku;
    private EditText editSMSText;
    /* Initialize SharedPreferences static parameters */
    public static final String SMSPreferences = "SMSPrefs";
    public static final String SMSText = "textKey";
    public static final String SMSTime = "timeKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        /* UP-button in ActionBar */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_previous);
        
        /* Get SharedPreferences and fill text fields */
        SharedPreferences shared = getSharedPreferences("SMSPrefs",MODE_PRIVATE);
        editSMSText = (EditText)findViewById(R.id.textfield_SMSText);
        btnTimupikku = (Button)findViewById(R.id.timupikku);
        editSMSText.setText(shared.getString("textKey", "null"));
        btnTimupikku.setText(shared.getString("timeKey", "null"));
        
        setListener();
    }
    
    /* Point buttons to UI and add listeners */
    public void setListener() {
        btnTimupikku.setOnClickListener(this);
        btnSave = (Button)findViewById(R.id.button_save);
        btnSave.setOnClickListener(this);
        btnStartService = (Button)findViewById(R.id.button_startService);
        btnStartService.setOnClickListener(this);
        btnStopService = (Button)findViewById(R.id.button_stopService);
        btnStopService.setOnClickListener(this);
    }
    
    /* Store current text field data in SharedPreferences  */
    public void setSMSPreferences() {
        /* Get smsText and smsTime */
        String smsText = editSMSText.getText().toString();
        String smsTime = btnTimupikku.getText().toString();
        /* Add smsText and smsTime to shared preferences */
        sharedpreferences = getSharedPreferences(SMSPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(SMSText, smsText);
        editor.putString(SMSTime, smsTime);
        editor.apply();
    }
    
    /* Inner class for TimePicker used to store smsTime */
    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            /* Use current system time as the default values for the picker */
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            // Create a new instance of TimePickerDialog and return it
            TimePickerDialog t = new TimePickerDialog(getActivity(),
                    this, hour, minute, DateFormat.is24HourFormat(getActivity()));
            return t;
        }
        /* Listens for user input and sets smsTime */
        public void onTimeSet(TimePicker view, int setHour, int setMinute) {
            Button b = (Button)getActivity().findViewById(R.id.timupikku);
            String selectedTime = formatTime(setHour,setMinute);
            b.setText(selectedTime);
        }
    }
    
    /* Function for formatting TimePicker output */
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

    /* Listens for button press and performs related actions */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.timupikku:
                TimePickerFragment tf = new TimePickerFragment();
                tf.show(getFragmentManager(), "Time Picker");
                break;
            case R.id.button_save:
                setSMSPreferences();
                Intent i1 = new Intent(context,SMSAlarm.class);
                stopService(i1);
                startService(i1);
                Log.d("Settings","Settings saved");
                break;
            case R.id.button_startService:
                Log.d("Settings", "Start service pressed");
                Intent i2 = new Intent(context,SMSAlarm.class);
                startService(i2);
                Log.d("SMSAlarm", "Service started");
                break;
            case R.id.button_stopService:
                Log.d("Settings", "Stop service pressed");
                Intent i3 = new Intent(context,SMSAlarm.class);
                stopService(i3);
                break;
        }
    }
}

