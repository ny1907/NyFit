package ny.nyfit;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by U820319 on 23.05.2017.
 */

public class InsertFragment extends Fragment implements View.OnClickListener{

    EditText lebensmittel;
    EditText kcal;
    EditText kohlenhydrate;
    EditText proteine;
    EditText fett;
    TextView error;
    Button save;
    Button delete;
    MySQLiteHelper db;

    Context context;
    String text = "Hinweis";
    int duration = Toast.LENGTH_SHORT;
    Toast toast;

    private OnFragmentInteractionListener mListener;

    public InsertFragment() {

    }

    public static InsertFragment newInsertFragment(){
        InsertFragment fragment = new InsertFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInsanceState) {
        super.onCreate(savedInsanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v =  inflater.inflate(R.layout.content_insert, container, false);

        context = getActivity();

        db = new MySQLiteHelper(getActivity());

        lebensmittel = (EditText) v.findViewById(R.id.editTextLebensmittel);
        kcal = (EditText) v.findViewById(R.id.editTextKcal);
        kohlenhydrate = (EditText) v.findViewById(R.id.editTextkohlenhydrate);
        proteine = (EditText) v.findViewById(R.id.editTextProteine);
        fett = (EditText) v.findViewById(R.id.editTextFett);
        error = (TextView) v.findViewById(R.id.textViewError);
        save = (Button) v.findViewById(R.id.buttonSpeichern);
        delete = (Button) v.findViewById(R.id.buttonVerwerfen);

        save.setOnClickListener(this);
        delete.setOnClickListener(this);

        int kcalnr, lebensmittelnr, kohlenhrdratenr, proteinenr, fettnr;

        final String errorText = "Default";
        error.setText(errorText);

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonSpeichern:
                save(v);
                break;
            case R.id.buttonVerwerfen:
                discard(v);
                break;
        }
    }

    public interface OnFragmentInteractionListener{
        void onFragmentInteraction(Uri uri);
    }

    public void save (View view) {
        if(keineLeerenFelder()){
            if(felderPlausibilisieren()){
                if(pruefeFeldlaengen()){

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

                        //TODO Testen: Hinweis, dass Lebensmittel gespeichert wurde
                       // context = getApplicationContext();
                        text = "Das Lebensmittel wurde hinzugefügt!";
                        toast = Toast.makeText(context, text, duration);
                        toast.show();

                        lebensmittel.setText("");
                        kcal.setText("");
                        kohlenhydrate.setText("");
                        proteine.setText("");
                        fett.setText("");

                    }

                }
            }
        }
    }

    private boolean keineLeerenFelder(){
        //context = getActivity();
        text = "Bitte alle Felder ausfüllen!";

        Boolean bLebensmittel = lebensmittel.getText().toString().trim().equals("");
        Boolean bKcal = kcal.getText().toString().trim().equals("");
        Boolean bKohlenhydrate = kohlenhydrate.getText().toString().trim().equals("");
        Boolean bProteine = proteine.getText().toString().trim().equals("");
        Boolean bFett = fett.getText().toString().trim().equals("");

        toast = Toast.makeText(context, text, duration);

        if (bLebensmittel || bKcal || bKohlenhydrate || bProteine || bFett){

            toast.show();
            return false;
        }
        else {
            return true;
        }

    }

    /*
     Prüft, ob Eingaben ok sind.
     return true: Alle Felder ok
     return false: Eines der Felder erfüllt nicht
      */
    private boolean felderPlausibilisieren(){
        //context = getActivity();
        text = "Unerlaubte Zeichen";
        toast = Toast.makeText(context, text, duration);
        // TODO Name: Nur Buchstaben, max. 30 Zeichen
        // TODO Kcal, Kohlenhydrate, Proteine, Fett: Nur Zahlen und EIN Punkt oder EIN Komma, max. 5 Zeichen
        if (!lebensmittel.getText().toString().trim().matches("[A-Za-z &\\-0-9]")){
            lebensmittel.requestFocus();
            toast.show();
            return false;
        }
        if (!kcal.getText().toString().trim().matches("[0-9.,]")){
            kcal.requestFocus();
            toast.show();
            return false;
        }
        if (!kohlenhydrate.getText().toString().trim().matches("[0-9.,]")){
            kohlenhydrate.requestFocus();
            toast.show();
            return false;
        }
        if (!proteine.getText().toString().trim().matches("[0-9.,]")){
            proteine.requestFocus();
            toast.show();
            return false;
        }
        if(!fett.getText().toString().trim().matches("[0-9.,]")){
            toast.show();
            fett.requestFocus();
            return false;
        }
        else{
            return true;
        }


    }

    private boolean pruefeFeldlaengen(){
  //      context = getActivity();
        text = "Das Feld enthält zu viele Zeichen";
        toast = Toast.makeText(context, text, duration);
        if(lebensmittel.length() > 30){
            lebensmittel.requestFocus();
            toast.show();
            return false;
        }
        if (kcal.length() > 10){
            kcal.requestFocus();
            toast.show();
            return false;
        }
        if (kohlenhydrate.length() > 10){
            kohlenhydrate.requestFocus();
            toast.show();
            return false;
        }
        if (proteine.length() > 10){
            proteine.requestFocus();
            toast.show();
            return false;
        }
        if (fett.length() > 10){
            fett.requestFocus();
            toast.show();
            return false;
        }
        else {
            return true;
        }
    }

    public void discard (View view){
        lebensmittel.setText("");
        kcal.setText("");
        kohlenhydrate.setText("");
        proteine.setText("");
        fett.setText("");
    }

}
