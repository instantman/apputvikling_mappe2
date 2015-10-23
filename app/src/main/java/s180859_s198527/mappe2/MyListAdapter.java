package s180859_s198527.mappe2;
/*
Custom adapter used to communicate with ListView
*/


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
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
import android.widget.Toast;

import java.util.List;

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

        // Inflate view during initial calls
        if(convertView == null){
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.listitem,parent,false);
            view.setTag(holder);
        }
        else{
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }

        // Ternary if to check for position and alternate between two colors.
        int clr = (position % 2 == 0 ? R.color.white : R.color.grey);
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(),clr));
        /* Setting viewholder references for item components*/
        holder.avatar = (ImageView)view.findViewById(R.id.listItem_avatar);
        holder.firstname = (TextView)view.findViewById(R.id.listItem_firstname);
        holder.lastname = (TextView)view.findViewById(R.id.listItem_lastname);
        holder.phone = (TextView)view.findViewById(R.id.listItem_phone);
        holder.birthdate = (TextView)view.findViewById(R.id.listItem_birthdate);
        holder.btn = (Button)view.findViewById(R.id.btnlol);
        holder.delete = (Button)view.findViewById(R.id.deletebtn);
        holder.firstNamePro = (EditText)view.findViewById(R.id.textfield_firstname);
        /* OnClickListener for deleting NEEDS FIXING PSPS PSPSPSPSPS*/
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.deletebtn:
                        DBHandler d = new DBHandler(v.getContext());
                        Contact c = mContacts.get(position);
                        Long deleteId = c.getDbId();
                        d.deleteContact(deleteId);
                        mContacts.remove(position);
                        getToast(2);
                        updateList();
                        break;
                }
            }
        });

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnlol:
                        AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                        final EditText inputFirst = new EditText(v.getContext());
                        inputFirst.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
                        final EditText inputLast = new EditText(v.getContext());
                        inputLast.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
                        inputLast.setTextAppearance(v.getContext(), R.style.Textfield);
                        /* PHONE EDITTEXT WITH INPUT VALIDATION*/
                        final EditText inputPhone = new EditText(v.getContext());
                        inputPhone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
                        inputPhone.setInputType(InputType.TYPE_CLASS_PHONE);
                        final TextView birthDate = new TextView(v.getContext());
                        // Set textview to Style with 70% opacity
                        birthDate.setTextAppearance(v.getContext(),R.style.UneditableTextfield);
                        // Added padding to birthdate field.
                        birthDate.setPadding(16, 4, 4, 4);
                        final DBHandler d = new DBHandler(v.getContext());
                        final Contact c = getItem(position);
                        alert.setTitle("Edit contact");
                        LinearLayout linearLayout = new LinearLayout(v.getContext());
                        linearLayout.setOrientation(LinearLayout.VERTICAL);
                        inputFirst.setText(c.getFirstname());
                        inputLast.setText(c.getLastname());
                        inputPhone.setText(c.getPhoneNr());
                        birthDate.setText(c.getBirthdate());
                        linearLayout.addView(inputFirst);
                        linearLayout.addView(inputLast);
                        linearLayout.addView(inputPhone);
                        linearLayout.addView(birthDate);
                        alert.setView(linearLayout);
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Long holdId = c.getDbId();
                                d.updateContact(c.getDbId(), inputFirst.getText().toString(), inputLast.getText().toString(), inputPhone.getText().toString(), birthDate.getText().toString());
                                d.close();
                                Contact c = new Contact(inputFirst.getText().toString(),
                                        inputLast.getText().toString(), inputPhone.getText().toString(), birthDate.getText().toString());
                                c.setDbId(holdId);
                                mContacts.set(position, c);
                                getToast(1);
                                dialog.dismiss();
                                updateList();
                            }
                        });
                        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // Cancel dialog
                                dialog.cancel();
                            }
                        });

                        AlertDialog alertDialog = alert.create();
                        alertDialog.show();
                }
            }});
        Contact contact = mContacts.get(position);
        holder.firstname.setText(contact.getFirstname());
        holder.lastname.setText(contact.getLastname());
        holder.phone.setText(contact.getPhoneNr());
        holder.birthdate.setText("Birthdate: "+contact.getBirthdate());
        return view;
    }

    public void getToast(int inId){
        if( inId==1 ) {
            Toast.makeText(mInflater.getContext(), R.string.contact_updated, Toast.LENGTH_LONG).show();
        }
        if(inId == 2 ){
            Toast.makeText(mInflater.getContext(), R.string.contact_deleted, Toast.LENGTH_LONG).show();
        }
    }

    public void updateList(){
        notifyDataSetChanged();
    }

    private  static class ViewHolder{
        protected ImageView avatar;
        protected TextView firstname,lastname,phone,birthdate,id;
        protected Button btn, delete;
        protected EditText firstNamePro;
    }
}
