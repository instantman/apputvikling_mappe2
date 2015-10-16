/** Activity to create and store objects in database */
package s180859_s198527.mappe2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegContact extends AppCompatActivity implements OnClickListener {

    private Button register;
    private String surname, lastname;
    private int phone;
    private int dateYear, dateMonth, dateDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regcontact);

        Log.d("Activity", "activity_regcontact created");


        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(this);
        register = (Button)findViewById(R.id.showTimePicker);
        register.setOnClickListener(this);
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
                Log.d("Button","Register-button pressed");
                // Oppretter nytt objekt i databasen (skal flyttes)
                DBHandler db = new DBHandler(this);
                Log.d("Legg inn:","legger til kontakter");
                db.addContact(new Contact("Ole", "Overjordet", 99449922, new Date()));
                break;
            case R.id.showTimePicker:
                DialogFragment df = new DatePickerFragment();
                df.show(getFragmentManager(),"Date Picker");
                break;
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

            // Returnerer DatePickerDialog objekt
            return new DatePickerDialog(getActivity(), this, year,month,day);
        }
        // Når ønsket dato er valgt fra DatePickerDialog
        public void onDateSet(DatePicker view, int year, int month, int day){
            TextView output = (TextView)getActivity().findViewById(R.id.outputDate);
            output.setText(day+"/"+(++month)+"/"+year);
        }
    }

}
