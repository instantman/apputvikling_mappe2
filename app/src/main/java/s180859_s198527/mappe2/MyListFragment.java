package s180859_s198527.mappe2;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Christopher on 19/10/2015.
 */
public class MyListFragment extends ListFragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DBHandler db = new DBHandler(getActivity());
        List<Contact> c = db.getAllContacts();
        Log.d("List", "ER HER");

        MyListAdapter mList = new MyListAdapter(getActivity(),c);
        setListAdapter(mList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
