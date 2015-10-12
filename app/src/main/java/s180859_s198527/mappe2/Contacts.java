package s180859_s198527.mappe2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Contacts extends AppCompatActivity implements OnClickListener{

    private Button newContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Log.d("Activity", "activity_contacts created");

        newContactButton = (Button)findViewById(R.id.button_newcontact);
        newContactButton.setOnClickListener(this);
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
            case R.id.button_newcontact:
                Intent i1 = new Intent(this,RegContact.class);
                startActivity(i1);
                break;
        }
    }

}
