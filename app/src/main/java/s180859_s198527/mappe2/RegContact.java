package s180859_s198527.mappe2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

public class RegContact extends AppCompatActivity implements OnClickListener {

    private Button register,showTimePicker;
    private String firstname, lastname, birthDate, phone;
    private int  startYear, startMonth, startDay;
    private Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regcontact);
        DatePickerFragment d = new DatePickerFragment();

        // Up-button in Actionbar.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_previous);

        final Calendar c = Calendar.getInstance();
        startDay = c.get(Calendar.DAY_OF_MONTH);
        startMonth = c.get(Calendar.MONTH);
        startYear = c.get(Calendar.YEAR);
        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(this);
        showTimePicker = (Button)findViewById(R.id.showTimePicker);
        showTimePicker.setText(d.formatDate(startYear,startMonth,startDay));
        showTimePicker.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            /* If registration complete, send back to contacts */
            case R.id.register:
                if(registerContact()){
                    Intent i = new Intent(this,Contacts.class);
                    startActivity(i);
                }
                break;
            case R.id.showTimePicker:
                DialogFragment df = new DatePickerFragment();
                df.show(getFragmentManager(), "Date Picker");
                break;
        }
    }
    /* Register new contact when button pressed */
    public boolean registerContact(){
        /* Instantiate validator */
        InputValidator val = new InputValidator();
        EditText firsT = (EditText)findViewById(R.id.textfield_firstname);
        EditText lastN = (EditText)findViewById(R.id.textfield_lastname);
        EditText phonez = (EditText)findViewById(R.id.textfield_phone);
        Button bDay = (Button)findViewById(R.id.showTimePicker);
        boolean inputvalidation = true;
        if(val.checkText(firsT)){
            firstname = firsT.getText().toString();
        }
        else if(!val.checkText(firsT)){
            inputvalidation = false;
        }
        if(val.checkText(lastN)){
            lastname = lastN.getText().toString();
        }
        else{
            inputvalidation = false;
        }
        if(val.checkText(phonez)){
            phone = phonez.getText().toString();
        }
        else{
            inputvalidation = false;
        }
        /* Validation passed - save new contact */
        if(inputvalidation){
            birthDate = bDay.getText().toString();
            DBHandler db = new DBHandler(this);
            Log.d("Legg inn:", "legger til kontakter!!" + firstname);
            db.addContact(new Contact(firstname, lastname, phone, birthDate));
            Toast.makeText(getApplicationContext(), "Contact saved...", Toast.LENGTH_LONG).show();
            db.close();
            return true;
        }
        else{
            return false;
        }
    }
    /* Class used for Validating input-strings */
    public class InputValidator{
        /* Check if string is empty, return errormessage */
        public boolean checkText(EditText e){
            if(e.getText().toString().length() == 0){
                e.setError("Cannot be empty");
                return false;
            }
            else{
                e.setError(null);
                return true;
            }
        }



    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Create new DatePickerDialogbox with today as value.
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // new DatePickerDialog objekt
            DatePickerDialog d = new DatePickerDialog(getActivity(), this, year,month,day);
            // Setting maxdate to today (Since birth date)
            d.getDatePicker().setMaxDate(new Date().getTime());
            // Return
            return d;
        }

        // Set selected date on button (formatted).
        public void onDateSet(DatePicker view, int year, int month, int day){
            Button b = (Button)getActivity().findViewById(R.id.showTimePicker);
            String selectedDate = formatDate(year,month,day);
            b.setText(selectedDate);
        }

        /* Format date to "yyyy-mm-dd" and adding zeros where needed*/
        public String formatDate(int year, int month, int day){
            String selectedDate;
            ++month;
            if(month<=9 && day<=9){
                selectedDate = Integer.toString(year)+"-0"+Integer.toString(month)+"-0"+Integer.toString(day);
                return selectedDate;
            }
            else if(month<=9){
                selectedDate = Integer.toString(year)+"-0"+Integer.toString(month)+"-"+Integer.toString(day);
                return selectedDate;
            }
            else if(day<=9){
                selectedDate = Integer.toString(year)+"-"+Integer.toString(month)+"-0"+Integer.toString(day);
                return selectedDate;
            }
            else{
                selectedDate = Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
                return selectedDate;
            }
        }
    }
}
