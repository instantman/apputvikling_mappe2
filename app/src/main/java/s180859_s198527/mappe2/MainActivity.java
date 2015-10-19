package s180859_s198527.mappe2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button contactsButton, settingsButton, exitButton;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        Log.d("Activity","activity_main created");

        // Knytter sammen knapper og legger på lytter
        contactsButton = (Button)findViewById(R.id.contactsButton);
        contactsButton.setOnClickListener(this);
        /*settingsButton = (Button)findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(this);*/
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
            /*case R.id.settingsButton:
                Intent i2 = new Intent(this,Settings.class);
                startActivity(i2);
                break;*/
            case R.id.exitButton:
                finish();
                System.exit(0);
                break;
        }
    }
}
