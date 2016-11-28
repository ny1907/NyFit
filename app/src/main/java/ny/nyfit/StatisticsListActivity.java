package ny.nyfit;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nasif on 26.11.16.
 */

public class StatisticsListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_list_view);*/
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);*/

        MySQLiteHelper db = new MySQLiteHelper(this);

        final ListView listview = (ListView) findViewById(R.id.listview);

        final ArrayList<String> list = new ArrayList<String>();
        List<Statistics> statsList = db.getAllStatistics();
        db.close();

        for (Statistics s : statsList){
            list.add(String.valueOf(s.getDatum()));
            list.add(String.valueOf(s.getGewicht()));
            list.add(String.valueOf(s.getFett()));
            list.add(String.valueOf(s.getMuskel()));
            list.add(String.valueOf(s.getWasser()));
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this, R.layout.activity_statistics_list_element, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
          //      showDetails(item);
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
    }

}
