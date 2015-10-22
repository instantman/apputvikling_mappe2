/** 
 * This will be the MainActivity when the application is finished.
 * Displays all saved contacts in a ListView with data from database.
 * Includes buttons and logic to start all other activities.
*/

package s180859_s198527.mappe2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Contacts extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        /* UP-button in ActionBar */
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_exit);
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

    /* Create and shows the ActionBar */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contacts, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Handles onClick-events in the ActionBar */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_addcontact:
                Intent i1 = new Intent(this,RegContact.class);
                startActivity(i1); // Starts RecContact-activity
                return true;
            case R.id.action_settings:
                Intent i2 = new Intent(this,Settings.class);
                startActivity(i2); // Starts Settings-activity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
