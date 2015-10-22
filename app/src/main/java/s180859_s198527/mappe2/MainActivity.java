/** 
 * This is a temporary MainActivity used to start and test activities and services.
 * PS! This activity will be deleted, and MainActivity will be Contacts. 
*/

package s180859_s198527.mappe2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button contactsButton, exitButton, testSMSButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add listeners to objects in view
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

    // View handling
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contactsButton:
                Intent i1 = new Intent(this,Contacts.class);
                startActivity(i1);
                break;
        }
    }
}
