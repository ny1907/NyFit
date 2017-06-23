package ny.nyfit;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U820319 on 24.05.2017.
 */

public class ListViewFragment extends Fragment {

    private InsertFragment.OnFragmentInteractionListener mListener;
    ArrayList<Food> foodList;

    public ListViewFragment() {

    }

    public static ListViewFragment newListViewFragment(){
        ListViewFragment fragment = new ListViewFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInsanceState) {
        super.onCreate(savedInsanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_list_view, container, false);

        MySQLiteHelper db = new MySQLiteHelper(getActivity());
        db.getReadableDatabase();

        final ListView listview = (ListView) v.findViewById(R.id.listview);

        final ArrayList<String> list = new ArrayList<String>();
        List<Food> foodList = db.allFoods();
        db.close();

        for (Food f : foodList){
            list.add(f.getName());
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                showDetails(item);
                //   view.animate().setDuration(2000).alpha(0)
                //           .withEndAction(new Runnable() {
                //               public void run() {
                // list.remove(item);
                //                   adapter.notifyDataSetChanged();
                //                   view.setAlpha(1);
                //               }
                //           });
            }
        });

        return v;
    }

    public void showDetails(String item){
        /*
        Intent intent = new Intent(getActivity(), ListViewActivityItem.class);
        intent.putExtra("item", item);
        startActivity(intent);
        */

        Fragment fragment = new ListViewActivityItemFragment();
        FragmentManager fm = getFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString("item", item);
        fragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.content_frame, fragment, "ListViewActivityItemFragment").addToBackStack(null).commit();
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener{
        void onFragmentInteraction(Uri uri);
    }
}
