package ny.nyfit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by U820319 on 13.10.2016.
 */

public class ListViewActivityItem extends AppCompatActivity{
    Food food;
    String nameFood;
    /*Float kcal;
    Float kohlenhydrate;
    Float proteine;
    Float fett;*/

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
        nameFood = (String) intent.getSerializableExtra("item");

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
        food = db.getFood(nameFood);
        db.close();

        // Textfelder mit Werten befüllen
        tname.setText(food.getName());
        tkcal.setText(String.valueOf(food.getKcal()));
        tkohlenhydrate.setText(String.valueOf(food.getKohlenhydrate()));
        tproteine.setText(String.valueOf(food.getProteine()));
        tfett.setText(String.valueOf(food.getFett()));

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
        eName.setText(food.getName());
        eKcal.setText(String.valueOf(food.getKcal()));
        eKohlenhydrate.setText(String.valueOf(food.getKohlenhydrate()));
        eProteine.setText(String.valueOf(food.getProteine()));
        eFett.setText(String.valueOf(food.getFett()));

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
        //TODO Prüfen, ob leere Felder vorhanden

        //Inhalte zwischenspeichern
        String tempName = eName.getText().toString().trim();
        String tempKcal = eKcal.getText().toString().trim();
        String tempKohlenhydrate = eKohlenhydrate.getText().toString().trim();
        String tempProteine = eProteine.getText().toString().trim();
        String tempFett = eFett.getText().toString().trim();

        if (tempName.equals("") || tempKcal.equals("") || tempKohlenhydrate.equals("") || tempProteine.equals("") || tempFett.equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("Leere Felder")
                    .setMessage("Alle Felder müssen befüllt werden!")
                    .setNegativeButton("Nein", null)
                    .show();

        }
        else{
            NumberFormat formatter = NumberFormat.getNumberInstance(Locale.GERMANY);

            //Buttons unsichtbar machen
            saveButton.setVisibility(ImageButton.INVISIBLE);
            discardButton.setVisibility(ImageButton.INVISIBLE);


            // Prüfen, ob Änderungen vorliegen
            if (!tempName.equals(food.getName()) || !tempKcal.equals(String.valueOf(food.getKcal())) || !tempKohlenhydrate.equals(String.valueOf(food.getKohlenhydrate())) || !tempProteine.equals(String.valueOf(food.getProteine())) || !tempFett.equals(String.valueOf(food.getFett()))){

                food.setName(tempName);
                try {
                    food.setKcal(formatter.parse(tempKcal).floatValue());
                    food.setKohlenhydrate(formatter.parse(tempKohlenhydrate).floatValue());
                    food.setProteine(formatter.parse(tempProteine).floatValue());
                    food.setFett(formatter.parse(tempFett).floatValue());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //Werte in die DB schreiben
                saveChangesOnDB(this.food);

                Context context = getApplicationContext();
                Toast.makeText(context, "Die Änderungen wurden gespeichert.", Toast.LENGTH_SHORT);
            }

            else {
                Context context = getApplicationContext();
                Toast.makeText(context, "Es wurde nichts verändert!", Toast.LENGTH_SHORT).show();
            }


            //Buttons sichtbar machen
            editButton.setVisibility(ImageButton.VISIBLE);
            deleteButton.setVisibility(ImageButton.VISIBLE);

            //EditTextfelder unsichtbar machen
            eName.setVisibility(editButton.INVISIBLE);
            eKcal.setVisibility(editButton.INVISIBLE);
            eKohlenhydrate.setVisibility(editButton.INVISIBLE);
            eProteine.setVisibility(editButton.INVISIBLE);
            eFett.setVisibility(editButton.INVISIBLE);

            //Textfelder mit aktuellen Werten befüllen
            tname.setText(food.getName());
            tkcal.setText(String.valueOf(food.getKcal()));
            tkohlenhydrate.setText(String.valueOf(food.getKohlenhydrate()));
            tproteine.setText(String.valueOf(food.getProteine()));
            tfett.setText(String.valueOf(food.getFett()));

            //Textfelder sichtbar machen
            tname.setVisibility(TextView.VISIBLE);
            tkcal.setVisibility(TextView.VISIBLE);
            tkohlenhydrate.setVisibility(TextView.VISIBLE);
            tproteine.setVisibility(TextView.VISIBLE);
            tfett.setVisibility(TextView.VISIBLE);

        }




    }

    private void saveChangesOnDB(Food food){
        MySQLiteHelper db = new MySQLiteHelper(this);
        db.getWritableDatabase();

        db.addFood(food);
        db.close();
    }
}
