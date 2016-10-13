package ny.nyfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by U820319 on 13.10.2016.
 */

public class ListViewActivityItem extends AppCompatActivity{
    Food food;
    String name;
    Float kcal;
    Float kohlenhydrate;
    Float proteine;
    Float fett;

    TextView tname;
    TextView tkcal;
    TextView tkohlenhydrate;
    TextView tproteine;
    TextView tfett;

    EditText eName;

    protected void onCreate(final Bundle savedInstanBundle){
        super.onCreate(savedInstanBundle);
        setContentView(R.layout.content_list_view_item);

        Intent intent = getIntent();
        name = (String) intent.getSerializableExtra("item");

        // IDs der Textfelder ermitteln
        tname = (TextView)findViewById(R.id.name);
        tkcal = (TextView) findViewById(R.id.kcal);
        tkohlenhydrate = (TextView)findViewById(R.id.kohlenhydrate);
        tproteine = (TextView)findViewById(R.id.proteine);
        tfett = (TextView)findViewById(R.id.fett);

        eName = (EditText)findViewById(R.id.editkohlenhydrate);

        // Datenbankverbindung öffnen
        MySQLiteHelper db = new MySQLiteHelper(this);
        db.getReadableDatabase();

        // Food-Objekt aus Datenbank erzeugen
        food = db.getFood(name);

        kcal = food.getKcal();
        kohlenhydrate = food.getKohlenhydrate();
        proteine = food.getProteine();
        fett = food.getFett();


        // Textfelder mit Werten befüllen
        tname.setText(name);
        tkcal.setText(kcal.toString());
        tkohlenhydrate.setText(kohlenhydrate.toString());
        tproteine.setText(proteine.toString());
        tfett.setText(fett.toString());

        db.close();
    }

    public void editFood(){
        eName.setText(this.name);
        eName.setVisibility(EditText.VISIBLE);
    }
}
