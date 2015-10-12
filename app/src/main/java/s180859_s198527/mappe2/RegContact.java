/** Activity to create and store objects in database */
package s180859_s198527.mappe2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import java.util.Date;

public class RegContact extends AppCompatActivity implements OnClickListener {

    private Button register, clearFields, back;
    private String surname, lastname;
    private int phone;
    private Date birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regcontact);
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

    // Tilbakeknapp i actionbar som sender tilbake til MainActivity
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(i);
        }
        return true;
    }

    // Tilbakeknapp på telefon, sender tilbak til HangMain
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getBaseContext().startActivity(i);
    }

    /* wats dis?

    public void registerContact(String surname,String lastname, int p, Date b) {
        s = surname;
        p = phone;
        b = birthdate;
    }*/

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:

                break;
        }
    }

}
