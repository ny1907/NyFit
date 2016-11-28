package ny.nyfit;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nasif on 26.11.16.
 */

public class StatisticsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        MySQLiteHelper db = new MySQLiteHelper(this);
        db.getReadableDatabase();

        final ListView listview = (ListView) findViewById(R.id.listview);

        final ArrayList<String> list = new ArrayList<String>();
        List<Statistics> statsList = db.getAllStatistics();
        db.close();

        for (Statistics s : statsList){
            list.add(String.valueOf(s.getGewicht()));
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
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
