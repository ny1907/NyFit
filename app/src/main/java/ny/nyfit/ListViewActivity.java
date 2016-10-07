package ny.nyfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    ArrayList<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MySQLiteHelper db = new MySQLiteHelper(this);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab2);

        //Intent intent = getIntent();
       // foodList = (ArrayList<Food>)getIntent().getSerializableExtra("Food");

        final ListView listview = (ListView) findViewById(R.id.listview);
        //String[] values = new String[] {"Volkan", "Hasan-Ali", "Skrtl", "Kjaer", "Van der Wiel",
        //                                "Topal", "Josef", "Ozan", "Volkan", "Emenike", "Lens", "Van Persie", "Sener", "Salih", "Ertugrul", "Neustaedter", "Sow", "Alper"};


        final ArrayList<String> list = new ArrayList<String>();
        List<Food> foodList = db.allFoods();

        for (Food f : foodList){
            list.add(f.getName());
        }

        //for (int i=0; i< values.length;++i) {
        //    list.add(values[i]);
        //}

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }
        });
    }

    public void addFood(){
        Intent intent = new Intent(this, InsertActivity.class);
        intent.putExtra("Foods", (ArrayList<Food>)foodList);
        startActivity(intent);
    }

}
//http://www.vogella.com/tutorials/AndroidListView/article.html