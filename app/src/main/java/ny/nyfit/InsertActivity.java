package ny.nyfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class InsertActivity extends AppCompatActivity {

    EditText lebensmittel;
    EditText kcal;
    EditText kohlenhydrate;
    EditText proteine;
    EditText fett;
    TextView error;
    List<Food> foods = new ArrayList<Food>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Button save = (Button) findViewById(R.id.buttonSpeichern);
        Button discard = (Button) findViewById(R.id.buttonVerwerfen);

        foods = (ArrayList<Food>)getIntent().getSerializableExtra("Food");
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

        if (lebensmittel.getText().toString().trim().equals("") ||
                kcal.getText().toString().trim().equals("") ||
                kohlenhydrate.getText().toString().trim().equals("") ||
                proteine.getText().toString().trim().equals("") ||
                fett.getText().toString().trim().equals("")) {

            error.setVisibility(TextView.VISIBLE);
        } else {
            // Neues Food Object wird erzeugt und mit den eingegebenen Daten befüllt.
            Food food = new Food(lebensmittel.getText().toString(), Float.valueOf(kcal.getText().toString()), Float.valueOf(kohlenhydrate.getText().toString()), Float.valueOf(proteine.getText().toString()), Float.valueOf(fett.getText().toString()));
            this.foods.add(food);

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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

