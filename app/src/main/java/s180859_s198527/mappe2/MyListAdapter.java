package s180859_s198527.mappe2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Contact> mContacts;

    public MyListAdapter(Context context, List<Contact> contacts){
        mInflater = LayoutInflater.from(context);
        mContacts = contacts;
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
            view = mInflater.inflate(R.layout.listitem,parent,false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            holder = new ViewHolder();

            int clr = (position % 2 == 0 ? R.color.white : R.color.grey);
            view.setBackgroundColor(ContextCompat.getColor(view.getContext(),clr));
            holder.avatar = (ImageView)view.findViewById(R.id.listItem_avatar);
            holder.firstname = (TextView)view.findViewById(R.id.listItem_firstname);
            holder.lastname = (TextView)view.findViewById(R.id.listItem_lastname);
            holder.phone = (TextView)view.findViewById(R.id.listItem_phone);
            holder.birthdate = (TextView)view.findViewById(R.id.listItem_birthdate);

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
            view.setTag(holder);
    }
        else{
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }
        Log.d("POS: ","~"+position);
        Contact contact = mContacts.get(position);
        holder.firstname.setText(contact.getSurname());
        holder.lastname.setText(contact.getLastname());
        holder.phone.setText(contact.getPhoneNr());
        holder.birthdate.setText(contact.getBirthdate());
        return view;
    }

    public Contact findContact(List<Contact> c,Long inLong){
        for(Contact cont : c){
            if(inLong == cont.getDbId()){
                return cont;
            }
        }
        return null;
    }

    private  static class ViewHolder{
        public ImageView avatar;
        public TextView firstname,lastname,phone,birthdate,id;
    }
}
