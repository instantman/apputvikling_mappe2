package s180859_s198527.mappe2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper{
    static String TABLE_CONTACTS = "Contacts";
    static String KEY_ID = "_ID";
    static String KEY_SURNAME= "Surname";
    static String KEY_LASTNAME = "Lastname";
    static String KEY_PHONENR = "Phonenumber";
    static String KEY_DATE = "Date";
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME ="Phonebook";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("+ KEY_ID + " INTEGER PRIMARY KEY, " + KEY_SURNAME + " TEXT, " + KEY_LASTNAME + " TEXT, " +
                KEY_PHONENR +" INTEGER, " + KEY_DATE + " TEXT" + ");";
        Log.d("SQL",CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CONTACTS);
        onCreate(db);
    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SURNAME,contact.getSurname());
        values.put(KEY_LASTNAME,contact.getLastname());
        values.put(KEY_PHONENR,contact.getPhoneNr());
        values.put(KEY_DATE,contact.getBirthdate());
        db.insert(TABLE_CONTACTS,null,values);
        db.close();
    }

    // NOT TESTET, NEED CHECKING OBS OBS OBS!!!
    public Contact getContact(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS,new String[]{KEY_ID,KEY_SURNAME,KEY_LASTNAME,KEY_PHONENR,KEY_DATE},KEY_ID+"=?", new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        Contact contact = new Contact(cursor.getString(1),cursor.getString(2),Integer.parseInt(cursor.getString(3)),cursor.getString(4));
        return contact;
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<Contact>();
        String sqlQ = "SELECT * FROM "+TABLE_CONTACTS+";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQ,null);
        if(cursor.moveToFirst()){
            do{
                Contact cnt = new Contact();
                cnt.setSurname(cursor.getString(1));
                cnt.setLastname(cursor.getString(2));
                cnt.setPhoneNr(cursor.getInt(3));
                cnt.setBirthdate(cursor.getString(4));
                contactList.add(cnt);
            }
            while(cursor.moveToNext());
        }
        return contactList;
    }
}
