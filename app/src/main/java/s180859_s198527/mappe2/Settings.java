package s180859_s198527.mappe2;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    private Switch switch_service;
    private EditText smstext, smsdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Oppknapp i ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_previous);

        // Knytter switch og tekstfelter til layout
        switch_service = (Switch)findViewById(R.id.switch_service);
        smstext = (EditText)findViewById(R.id.textfield_SMStext);
        smsdate = (EditText)findViewById(R.id.textfield_SMSdate);

        // Setter switch til "ON"
        switch_service.setChecked(true);
        // Legger til lytter og funksjonalitet
        switch_service.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Log.d("Switch", "ON");
                } else {
                    Log.d("Switch", "OFF");
                }
            }
        });
    }

    // Metoder for å sette smstext og smsdate
    public void setSmstext (String inText) {
        smstext.setText(inText);
    }
    public void setSmsdate (String inDate) {
        smsdate.setText(inDate);
    }

    // Metoder for å hente smstext og smsdate
    public String getSmstext() {
        return smstext.toString();
    }
    public String getSmsdate() {
        return smsdate.toString();
    }

}
