package s180859_s198527.mappe2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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

    private Button register,showTimePicker,showAll;
    private String surname, lastname, birthDate, phone;
    private int  startYear, startMonth, startDay;
    private Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regcontact);
        Log.d("Activity", "activity_regcontact created");
        DatePickerFragment d = new DatePickerFragment();

        // Oppknapp i ActionBar
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
            case R.id.register:
                registerContact();
                /*Intent i = new Intent(this,Contacts.class);
                startActivity(i);*/
                break;
            case R.id.showTimePicker:
                DialogFragment df = new DatePickerFragment();
                df.show(getFragmentManager(), "Date Picker");
                break;
        }
    }

    public void registerContact(){
        Log.d("Button","Register-button pressed");
        // Oppretter nytt objekt i databasen (skal flyttes)
        InputValidator val = new InputValidator();
        EditText surN = (EditText)findViewById(R.id.textfield_surname);
        EditText lastN = (EditText)findViewById(R.id.textfield_lastname);
        EditText phonez = (EditText)findViewById(R.id.textfield_phone);
        Button bDay = (Button)findViewById(R.id.showTimePicker);
        boolean inputvalidationTrue = true;

        if(val.checkText(surN)){
            surname = surN.getText().toString();
        }
        else if(!val.checkText(surN)){
            inputvalidationTrue = false;
        }
        if(val.checkText(lastN)){
            lastname = lastN.getText().toString();
        }
        else{
            inputvalidationTrue = false;
        }
        if(val.checkText(phonez)){
            phone = phonez.getText().toString();
        }
        else{
            inputvalidationTrue = false;
        }
        if(inputvalidationTrue){
            birthDate = bDay.getText().toString();
            DBHandler db = new DBHandler(this);
            Log.d("Legg inn:", "legger til kontakter!!");
            db.addContact(new Contact(surname, lastname, phone, birthDate));
            Toast.makeText(getApplicationContext(), "Contact saved...", Toast.LENGTH_LONG).show();
            db.close();
        }
    }

    public class InputValidator{
        public boolean checkText(EditText e){
            if(e.getText().toString().length() == 0){
                e.setError("Cannot be empty");
                return false;
            }
            else{
                surname = e.getText().toString();
                return true;
            }
        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Lager ny DatePickerDialogboks med d.d. som startverdi
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Opretter DatePickerDialog objekt
            DatePickerDialog d = new DatePickerDialog(getActivity(), this, year,month,day);
            // Setter maxdato til dagens dato (siden fødselsdato).
            d.getDatePicker().setMaxDate(new Date().getTime());
            // Returnerer
            return d;
        }

        // Når ønsket dato er valgt fra DatePickerDialog
        public void onDateSet(DatePicker view, int year, int month, int day){
            Button b = (Button)getActivity().findViewById(R.id.showTimePicker);
            String selectedDate = formatDate(year,month,day);
            b.setText(selectedDate);
        }

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
                Log.d("HALLO",selectedDate);
                return selectedDate;
            }
        }
    }
}
