package ny.nyfit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by U820319 on 24.11.2016.
 */

public class Statistics {
    private int id;
    private float gewicht;
    private float fett;
    private float muskel;
    private float wasser;
    private String datum;

    public Statistics(float gewicht){
        this.gewicht = gewicht;
    }


    public Statistics(float gewicht, float fett, float muskel, float wasser){
        this.id = id;
        this.gewicht = gewicht;
        this.fett = fett;
        this.muskel = muskel;
        this.wasser = wasser;
    }

    private String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        return dateFormat.format(date.getTime());
    }

    public int getID(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getGewicht(){
        return this.gewicht;
    }

    public float getFett(){
        return this.fett;
    }

    public float getMuskel(){
        return this.muskel;
    }

    public float getWasser(){
        return this.wasser;
    }

    public String getDatum(){
        return this.datum;
    }

    public void setCurrentDatum() {
        this.datum = getCurrentDate();
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String toString(){
        return "Statistics[id=" + id + "; gewicht=" + gewicht + "; fett=" + fett + "; muskel=" + muskel + "; wasser=" + wasser + "; datum=" + datum + "]";
    }
}
