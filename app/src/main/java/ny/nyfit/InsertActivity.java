package ny.nyfit;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static ny.nyfit.R.string.kcal;
import static ny.nyfit.R.string.kohlenhydrate;
import static ny.nyfit.R.string.lebensmittel;

public class InsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button save = (Button) findViewById(R.id.buttonSpeichern);
        Button discard = (Button) findViewById(R.id.buttonVerwerfen);

        final EditText lebensmittel = (EditText) findViewById(R.id.editTextLebensmittel);
        final EditText kcal = (EditText) findViewById(R.id.editTextKcal);
        final EditText kohlenhydrate = (EditText) findViewById(R.id.editTextkohlenhydrate);
        final EditText proteine = (EditText) findViewById(R.id.editTextProteine);
        final EditText fett = (EditText) findViewById(R.id.editTextFett);
        final TextView error = (TextView) findViewById(R.id.textViewError);

        int kcalnr, lebensmittelnr, kohlenhrdratenr, proteinenr, fettnr;

        final String errorText = "Alle Felder müssen befüllt werden!";
        error.setText(errorText);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    public void save (View view) {

        if (lebensmittel.getText().toString().trim().equals("") ||
                kcal.getText().toString().trim().equals("") ||
                kohlenhydrate.getText().toString().trim().equals("") ||
                proteine.getText().toString().trim().equals("") ||
                fett.getText().toString().trim().equals("")) {

            error.setVisibility(TextView.VISIBLE);
        } else {

        }
    }
        public void discard (View view){
            setContentView(R.layout.activity_main);
        }

}

