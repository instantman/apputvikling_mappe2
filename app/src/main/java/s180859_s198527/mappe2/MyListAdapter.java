package s180859_s198527.mappe2;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Christopher on 19/10/2015.
 */
public class MyListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Contact> mContacts;

    public MyListAdapter(Context context, List<Contact> contacts){
        this.mInflater = LayoutInflater.from(context);
        this.mContacts = contacts;
    }



    @Override
    public int getCount() {
        return mContacts.size();
    }

    @Override
    public Contact getItem(int position) {
        return mContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.listitem,parent,false);
            view.setTag(holder);
    }
        else{
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }

        int clr = (position % 2 == 0 ? R.color.white : R.color.grey);
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(),clr));
        holder.avatar = (ImageView)view.findViewById(R.id.listItem_avatar);
        holder.firstname = (TextView)view.findViewById(R.id.listItem_firstname);
        holder.lastname = (TextView)view.findViewById(R.id.listItem_lastname);
        holder.phone = (TextView)view.findViewById(R.id.listItem_phone);
        holder.birthdate = (TextView)view.findViewById(R.id.listItem_birthdate);
        holder.btn = (Button)view.findViewById(R.id.btnlol);

        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.listItem_avatar:
                        DBHandler d = new DBHandler(v.getContext());
                        Log.d("IDIDIDID", "is: " + position);
                        Contact c = mContacts.get(position);
                        Log.d("Hallo",": \n"+c.getSurname()+"\n"+c.getLastname()+"\n"+c.getDbId());
                        Long deleteId = c.getDbId();
                        d.deleteContact(deleteId);
                        mContacts.remove(position);
                        notifyDataSetChanged();
                        break;
                }
            }
        });

        holder.btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switch (v.getId()){
                    case R.id.btnlol:
                        Log.d("Button pressed ID:" , " "+getItem(position).getDbId()+"---"+position);
                        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                        final EditText inputFirst = new EditText(v.getContext());
                        final EditText inputLast = new EditText(v.getContext());
                        final EditText inputPhone = new EditText(v.getContext());
                        final Button inputDate = new Button(v.getContext());
                        final DBHandler d = new DBHandler(v.getContext());
                        final Contact c = getItem(position);
                        alert.setTitle("Hola! Edito contacto por favor");
                        LinearLayout linearLayout = new LinearLayout(v.getContext());
                        linearLayout.setOrientation(LinearLayout.VERTICAL);
                        inputPhone.setText(c.getPhoneNr());
                        inputLast.setText(c.getLastname());
                        inputFirst.setText(c.getSurname());
                        inputDate.setText("1999-12-10");
                        linearLayout.addView(inputFirst);
                        linearLayout.addView(inputLast);
                        linearLayout.addView(inputPhone);
                        linearLayout.addView(inputDate);
                        alert.setView(linearLayout);

                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichbutton) {
                                d.updateContact(c.getDbId(), inputFirst.getText().toString(), inputLast.getText().toString(), inputPhone.getText().toString(), inputDate.getText().toString());
                                d.close();
                                Contact c = new Contact(inputFirst.getText().toString(),
                                        inputLast.getText().toString(), inputPhone.getText().toString(), inputDate.getText().toString());
                                mContacts.set(position,c);
                                dialog.dismiss();
                                updateList();
                            }
                        });
                        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Canceled.
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = alert.create();
                        alertDialog.show();
                }
            }});
        Log.d("POS: ","~"+position);
        Contact contact = mContacts.get(position);
        holder.firstname.setText(contact.getSurname());
        holder.lastname.setText(contact.getLastname());
        holder.phone.setText(contact.getPhoneNr());
        holder.birthdate.setText(contact.getBirthdate());
        return view;
    }

    public void updateList(){
        notifyDataSetChanged();
    }


    private  static class ViewHolder{
        protected ImageView avatar;
        protected TextView firstname,lastname,phone,birthdate,id;
        protected Button btn;
    }
}
