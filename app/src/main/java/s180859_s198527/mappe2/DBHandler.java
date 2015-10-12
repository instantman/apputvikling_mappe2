package s180859_s198527.mappe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by Christopher on 12/10/2015.
 */
public class DBHandler extends SQLiteOpenHelper{

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_SURNAME + " TEXT," + KEY_LASTNAME + " TEXT," +
                KEY_PHONENR +" INTEGER," + KEY_DATE + " DATE" + ")";
        Log.d("SQL",CREATE_TABLE);
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CONTACTS);
        onCreate(db);
    }


    static String TABLE_CONTACTS = "Contacts";
    static String KEY_ID = "_ID";
    static String KEY_SURNAME= "Surname";
    static String KEY_LASTNAME = "Lastname";
    static String KEY_PHONENR = "Phonenumber";
    static String KEY_DATE = "Date";
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME ="Phonecontacts";

    public DBHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SURNAME,contact.getSurname());
        values.put(KEY_LASTNAME,contact.getLastname());
        values.put(KEY_PHONENR,contact.getPhoneNr());
        values.put(KEY_DATE,contact.getBirthdate().getTime());
        db.insert(TABLE_CONTACTS,null,values);
        db.close();
    }

}
