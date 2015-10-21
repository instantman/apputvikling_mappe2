package s180859_s198527.mappe2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

    private EditText smstext, smsdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Oppknapp i ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_previous);

        smstext = (EditText)findViewById(R.id.textfield_SMStext);
        smsdate = (EditText)findViewById(R.id.textfield_SMSdate);
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

