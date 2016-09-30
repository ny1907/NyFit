package ny.nyfit;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by U820319 on 30.09.2016.
 */

public class Food implements Serializable{
    private String name;
    private float kcal;
    private float kohlenhydrate;
    private float proteine;
    private float fett;
    private HashMap<String, Float> einheit;

    public Food(String name, float kcal, float kohlenhydrate, float proteine, float fett) {
        this.name = name;
        this.kcal = kcal;
        this.proteine = proteine;
        this.fett = fett;
    }


    public String getName(){
        return this.name;
    }

    public float getKcal(){
        return this.kcal;
    }

    public float getKohlenhydrate(){
        return this.kohlenhydrate;
    }

    public float getProteine(){
        return this.proteine;
    }

    public float getFett(){
        return this.fett;
    }

}
