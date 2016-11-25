package ny.nyfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ArrayList<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        MySQLiteHelper db = new MySQLiteHelper(this);
        db.getReadableDatabase();

        final ListView listview = (ListView) findViewById(R.id.listview);

        final ArrayList<String> list = new ArrayList<String>();
        List<Food> foodList = db.allFoods();
        db.close();

        for (Food f : foodList){
            list.add(f.getName());
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
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
    }

    public void addFood(){
        Intent intent = new Intent(this, InsertActivity.class);
        intent.putExtra("Foods", (ArrayList<Food>)foodList);
        startActivity(intent);
    }

    public void showDetails(String item){
        Intent intent = new Intent(this, ListViewActivityItem.class);
        intent.putExtra("item", item);
        startActivity(intent);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add_foods) {
            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_foodlist) {
            Intent intent = new Intent(this, ListViewActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_list1) {

        } else if (id == R.id.nav_list2) {

        } else if (id == R.id.nav_add) {
            Intent intent = new Intent(this, StatisticsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_show) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
//http://www.vogella.com/tutorials/AndroidListView/article.html

//TODO https://github.com/liuzc/android-swipelistview