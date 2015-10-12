package s180859_s198527.mappe2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Date;

public class Contacts extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        Log.d("Her", "Er jeg!");

        DBHandler db = new DBHandler(this);
        Log.d("Legg inn:","legger til kontakter");
        db.addContact(new Contact("Ole","Overjordet",99449922,new Date()));
    }
}
