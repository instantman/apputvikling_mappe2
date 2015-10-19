package s180859_s198527.mappe2;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Christopher on 19/10/2015.
 */
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
    public Object getItem(int position) {
        return mContacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if(convertView == null){
            view = mInflater.inflate(R.layout.listitem,parent,false);
            holder = new ViewHolder();
            holder.avatar = (ImageView)view.findViewById(R.id.imageView1);
            holder.firstname = (TextView)view.findViewById(R.id.listItem_firstname);
            holder.lastname = (TextView)view.findViewById(R.id.listItem_lastname);
            holder.phone = (TextView)view.findViewById(R.id.listItem_phone);
            holder.birthdate = (TextView)view.findViewById(R.id.listItem_birthdate);
            view.setTag(holder);
        }
        else{
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }

        Contact contact = mContacts.get(position);
        holder.firstname.setText(contact.getSurname());
        holder.lastname.setText(contact.getLastname());
        holder.phone.setText(Integer.toString(contact.getPhoneNr()));
        holder.birthdate.setText(contact.getBirthdate());

        return view;
    }

    private class ViewHolder{
        public ImageView avatar;
        public TextView firstname,lastname,phone,birthdate;
    }
}
