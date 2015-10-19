package s180859_s198527.mappe2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    private Switch switch_service;
    private EditText smstext, smsdate;
    private Button btnStartService, btnStopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Oppknapp i ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_previous);

        // Knytter switch og tekstfelter til layout
        /*btnStartService = (Button)findViewById(R.id.button_startService);
        btnStopService = (Button)findViewById(R.id.button_stopService);*/

        /*switch_service = (Switch)findViewById(R.id.switch_service);
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
        });*/
    }

    public void startService(View view) {
        Intent intent = new Intent(this,SMSService.class);
        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this,SMSService.class);
        stopService(intent);
    }

}

