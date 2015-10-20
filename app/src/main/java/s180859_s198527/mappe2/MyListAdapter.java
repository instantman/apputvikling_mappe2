package s180859_s198527.mappe2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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
public class MyListAdapter extends BaseAdapter implements View.OnClickListener {

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
            /*if(position %2==0)
            {
                view.setBackgroundColor(view.getResources().getColor(R.color.white));
            }
            else
            {
                view.setBackgroundColor(view.getResources().getColor(R.color.grey));
            }*/

            int clr = (position % 2 == 0 ? R.color.white : R.color.grey);
            view.setBackgroundColor(ContextCompat.getColor(view.getContext(),clr));

            holder.avatar = (ImageView)view.findViewById(R.id.listItem_avatar);
            holder.firstname = (TextView)view.findViewById(R.id.listItem_firstname);
            holder.lastname = (TextView)view.findViewById(R.id.listItem_lastname);
            holder.phone = (TextView)view.findViewById(R.id.listItem_phone);
            holder.birthdate = (TextView)view.findViewById(R.id.listItem_birthdate);


            holder.avatar.setOnClickListener(this);
            view.setTag(holder);
        }
        else{
            view = convertView;
            holder = (ViewHolder)view.getTag();
        }

        Contact contact = mContacts.get(position);
        holder.firstname.setText(contact.getSurname());
        holder.lastname.setText(contact.getLastname());
        holder.phone.setText(contact.getPhoneNr());
        holder.birthdate.setText(contact.getBirthdate());

        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.listItem_avatar:
                Log.d("BUTTON","CLICKED FFS");
                break;
        }
    }

    private class ViewHolder{
        public ImageView avatar;
        public TextView firstname,lastname,phone,birthdate;
    }
}
