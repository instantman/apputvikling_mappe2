/** Activity to create and store objects in database */
package s180859_s198527.mappe2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.util.Date;

public class RegContact extends AppCompatActivity implements OnClickListener {

    private Button register;
    private String surname, lastname;
    private int phone;
    private Date birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regcontact);

        Log.d("Activity", "activity_regcontact created");

        register = (Button)findViewById(R.id.register);
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
                break;
        }
    }

}