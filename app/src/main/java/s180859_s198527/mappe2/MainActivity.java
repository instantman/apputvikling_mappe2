package s180859_s198527.mappe2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button contactsButton, exitButton, testSMSButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar c = Calendar.getInstance();
        Log.d("Activity", c.getTime().toString());

        // Knytter sammen knapper og legger på lytter
        contactsButton = (Button)findViewById(R.id.contactsButton);
        contactsButton.setOnClickListener(this);
        testSMSButton = (Button)findViewById(R.id.testSMSButton);
        testSMSButton.setOnClickListener(this);
        exitButton = (Button)findViewById(R.id.exitButton);
        exitButton.setOnClickListener(this);
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

    // Håndterer hva som skjer når knapper blir trykket
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contactsButton:
                Intent i1 = new Intent(this,Contacts.class);
                startActivity(i1);
                break;
            case R.id.testSMSButton:
                Intent i2 = new Intent(this,SendSMS.class);
                startActivity(i2);
                break;
            case R.id.exitButton:

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DAY_OF_MONTH, 0);
                Date date = calendar.getTime();
                String fDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
                String fTime = new SimpleDateFormat("HH:mm").format(date);
                Log.d("Mer dato","Datoen er "+fDate+", tiden er "+fTime);

                DBHandler d = new DBHandler(this);
                List<Contact> c = d.getAllContacts();
                Log.d("SendSMS",""+c.size());
                SendSMS smsSender = new SendSMS();
                for(Contact cont : c ){
                    Log.d("22",cont.getBirthdate());
                    if(cont.getBirthdate().equals(fDate)){
                        smsSender.sendSMSMessage(cont.getSurname()+" "+cont.getLastname(), cont.getPhoneNr());
                        Log.d("SMS sent to", cont.getPhoneNr());
                    }
                    else {
                        Log.d("SMS","No burfdaiz :(");
                    }
                }
                break;
        }
    }
}
