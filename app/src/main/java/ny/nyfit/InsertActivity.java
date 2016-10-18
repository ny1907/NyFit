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
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class InsertActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    EditText lebensmittel;
    EditText kcal;
    EditText kohlenhydrate;
    EditText proteine;
    EditText fett;
    TextView error;
    MySQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        db = new MySQLiteHelper(this);

        lebensmittel = (EditText) findViewById(R.id.editTextLebensmittel);
        kcal = (EditText) findViewById(R.id.editTextKcal);
        kohlenhydrate = (EditText) findViewById(R.id.editTextkohlenhydrate);
        proteine = (EditText) findViewById(R.id.editTextProteine);
        fett = (EditText) findViewById(R.id.editTextFett);
        error = (TextView) findViewById(R.id.textViewError);

        int kcalnr, lebensmittelnr, kohlenhrdratenr, proteinenr, fettnr;

        final String errorText = "Alle Felder müssen befüllt werden!";
        error.setText(errorText);
    }

    public void save (View view) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.GERMANY);

        String tempLebensmittel = lebensmittel.getText().toString().trim();
        Float tempKcal = null;
        Float tempKohlenhydrate = null;
        Float tempProteine = null;
        Float tempFett = null;

        try {
            tempKcal = formatter.parse(kcal.getText().toString().trim()).floatValue();
            tempKohlenhydrate = formatter.parse(kohlenhydrate.getText().toString().trim()).floatValue();
            tempProteine = formatter.parse(proteine.getText().toString().trim()).floatValue();
            tempFett = formatter.parse(fett.getText().toString().trim()).floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (tempLebensmittel.equals("") ||
                tempKcal.toString().equals("") ||
                tempKohlenhydrate.toString().equals("") ||
                tempProteine.toString().equals("") ||
                tempFett.toString().equals("")) {

            error.setVisibility(TextView.VISIBLE);
        } else {

            // Neues Food Object wird erzeugt und mit den eingegebenen Daten befüllt.
            Food food = new Food(tempLebensmittel, tempKcal, tempKohlenhydrate, tempProteine, tempFett);

            db.addFood(food);

            //TODO Hinweis, dass Lebensmittel gespeichert wurde

            lebensmittel.setText("");
            kcal.setText("");
            kohlenhydrate.setText("");
            proteine.setText("");
            fett.setText("");

        }
    }

    public void discard (View view){
            lebensmittel.setText("");
            kcal.setText("");
            kohlenhydrate.setText("");
            proteine.setText("");
            fett.setText("");
    }

    @Override
    public void onBackPressed() {
      //  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
      //  if (drawer.isDrawerOpen(GravityCompat.START)) {
      //      drawer.closeDrawer(GravityCompat.START);
      //  } else {
            super.onBackPressed();
      //  }
    }

    @Override
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

        } else if (id == R.id.nav_show) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

