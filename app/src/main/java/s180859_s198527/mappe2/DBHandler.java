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
    /* Values used in Database-handling */
    static String TABLE_CONTACTS = "Contacts";
    static String KEY_ID = "_ID";
    static String KEY_FIRSTNAME= "Firstname";
    static String KEY_LASTNAME = "Lastname";
    static String KEY_PHONENR = "Phonenumber";
    static String KEY_DATE = "Date";
    static int DATABASE_VERSION = 1;
    static String DATABASE_NAME ="Final_DB";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /* Create database*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("+ KEY_ID + " INTEGER PRIMARY KEY, " + KEY_FIRSTNAME + " TEXT, " + KEY_LASTNAME + " TEXT, " +
                KEY_PHONENR +" TEXT, " + KEY_DATE + " TEXT" + ");";
        Log.d("SQL", CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_CONTACTS);
        onCreate(db);
    }

    /* Delete row in Contacts based in ID*/
    public void deleteContact(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        if (db == null) {
            return;
        }
        db.delete(TABLE_CONTACTS, "_id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    /* Insert new contact */
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, contact.getFirstname());
        values.put(KEY_LASTNAME, contact.getLastname());
        values.put(KEY_PHONENR,contact.getPhoneNr());
        values.put(KEY_DATE, contact.getBirthdate());
        /* Get unique id from DB and add to Contact*/
        long insertid = db.insert(TABLE_CONTACTS, null, values);
        contact.setDbId(insertid);
        db.close();
    }
    /* Fill Custom List with Contacts from DB and return*/
    public List<Contact> getAllContacts(){
        List<Contact> contactList = new ArrayList<Contact>();
        String sqlQ = "SELECT * FROM "+TABLE_CONTACTS+";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQ,null);
        if(cursor.moveToFirst()){
            do{
                Contact cnt = new Contact();
                cnt.setFirstname(cursor.getString(1));
                cnt.setLastname(cursor.getString(2));
                cnt.setPhoneNr(cursor.getString(3));
                cnt.setBirthdate(cursor.getString(4));
                cnt.setDbId(cursor.getLong(0));
                contactList.add(cnt);
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactList;

    }
    /* Update contact */
    public void updateContact(Long id, String inFirstname, String inLastname, String inPhone, String inBirthdate){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FIRSTNAME, inFirstname);
        values.put(KEY_LASTNAME, inLastname);
        values.put(KEY_PHONENR, inPhone);
        values.put(KEY_DATE, inBirthdate);
        /* Update contact with ID = */
        db.update(TABLE_CONTACTS,values, "_id = ?", new String[] { String.valueOf(id) });
        db.close();
    }
}
