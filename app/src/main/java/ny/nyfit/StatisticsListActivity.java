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
        /*setContentView(R.layout.content_list_view);*/
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);*/

        MySQLiteHelper db = new MySQLiteHelper(this);

        final ArrayList<String> list = new ArrayList<String>();
        List<Statistics> statsList = db.getAllStatistics();
        db.close();

        for (Statistics s : statsList){
            /*list.add(String.valueOf(s.getDatum()));
            list.add(String.valueOf(s.getID()));
            list.add(String.valueOf(s.getGewicht()));
            list.add(String.valueOf(s.getFett()));
            list.add(String.valueOf(s.getMuskel()));
            list.add(String.valueOf(s.getWasser()));*/
            list.add(s.toString());
        }

        final StatisticsArrayAdapter adapter = new StatisticsArrayAdapter(this, list);
        setListAdapter(adapter);

    }

}
