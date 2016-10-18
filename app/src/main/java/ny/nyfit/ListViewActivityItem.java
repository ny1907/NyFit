package ny.nyfit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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

    private TextView tname;
    private TextView tkcal;
    private TextView tkohlenhydrate;
    private TextView tproteine;
    private TextView tfett;

    private EditText eName;
    private EditText eKcal;
    private EditText eKohlenhydrate;
    private EditText eProteine;
    private EditText eFett;

    private ImageButton editButton;
    private ImageButton saveButton;
    private ImageButton discardButton;
    private ImageButton deleteButton;


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

        // IDs der Eingabefelder
        eName = (EditText)findViewById(R.id.editname);
        eKcal = (EditText)findViewById(R.id.editkcal);
        eKohlenhydrate = (EditText)findViewById(R.id.editkohlenhydrate);
        eProteine = (EditText)findViewById(R.id.editproteine);
        eFett = (EditText)findViewById(R.id.editfett);

        // IDs der Buttons
        editButton = (ImageButton)findViewById(R.id.editButton);
        saveButton = (ImageButton)findViewById(R.id.saveButton);
        discardButton = (ImageButton)findViewById(R.id.discardButton);
        deleteButton = (ImageButton)findViewById(R.id.deleteButton);

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

    public void editFood(View view){
        // Buttons unsichtbar machen
        editButton.setVisibility(ImageButton.INVISIBLE);
        deleteButton.setVisibility(ImageButton.INVISIBLE);

        // Textfelder unsichtbar machen
        tname.setVisibility(TextView.INVISIBLE);
        tkcal.setVisibility(TextView.INVISIBLE);
        tkohlenhydrate.setVisibility(TextView.INVISIBLE);
        tproteine.setVisibility(TextView.INVISIBLE);
        tfett.setVisibility(TextView.INVISIBLE);

        // Eingabefelder vorbefüllen
        eName.setText(this.name);
        eKcal.setText(this.kcal.toString());
        eKohlenhydrate.setText(this.kohlenhydrate.toString());
        eProteine.setText(this.proteine.toString());
        eFett.setText(this.fett.toString());

        // Buttons sichtbar machen
        saveButton.setVisibility(ImageButton.VISIBLE);
        discardButton.setVisibility(ImageButton.VISIBLE);

        // Eingabefelder sichtbar machen
        eName.setVisibility(EditText.VISIBLE);
        eKcal.setVisibility(EditText.VISIBLE);
        eKohlenhydrate.setVisibility(EditText.VISIBLE);
        eProteine.setVisibility(EditText.VISIBLE);
        eFett.setVisibility(EditText.VISIBLE);
    }

    // Discard Changes
    public void discardChanges(View view){
        // Buttons unsichtbar machen
        discardButton.setVisibility(ImageButton.INVISIBLE);
        saveButton.setVisibility(ImageButton.INVISIBLE);

        // Eingabefelder unsichtbar machen
        eName.setVisibility(ImageButton.INVISIBLE);
        eKcal.setVisibility(ImageButton.INVISIBLE);
        eKohlenhydrate.setVisibility(ImageButton.INVISIBLE);
        eProteine.setVisibility(ImageButton.INVISIBLE);
        eFett.setVisibility(ImageButton.INVISIBLE);

        // Eingabefelder leeren
        eName.setText("");
        eKcal.setText("");
        eKohlenhydrate.setText("");
        eProteine.setText("");
        eFett.setText("");

        // Textfelder sichtbar machen
        tname.setVisibility(TextView.VISIBLE);
        tkcal.setVisibility(TextView.VISIBLE);
        tkohlenhydrate.setVisibility(TextView.VISIBLE);
        tproteine.setVisibility(TextView.VISIBLE);
        tfett.setVisibility(TextView.VISIBLE);

        // Buttons sichtbar machen
        editButton.setVisibility(ImageButton.VISIBLE);
        deleteButton.setVisibility(ImageButton.VISIBLE);
    }

    // Delete Food from Database
    public void deleteFood(View view){

        new AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Löschen")
            .setMessage("Wirklich Löschen?")
            .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   deleteFoodFromDB();
                }
            })
            .setNegativeButton("Nein", null)
            .show();

    }

    private void deleteFoodFromDB(){
        MySQLiteHelper db = new MySQLiteHelper(this);
        db.getWritableDatabase();

        db.delete(this.food);
        db.close();


        Intent intent = new Intent(this, ListViewActivity.class);
        startActivity(intent);
        finish();
    }

    public void saveChanges(View view) {
    }
}
