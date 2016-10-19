package ny.nyfit;

import java.io.Serializable;

/**
 * Created by U820319 on 30.09.2016.
 */

public class Food implements Serializable{
    private int id;
    private String name;
    private float kcal;
    private float kohlenhydrate;
    private float proteine;
    private float fett;
//    private HashMap<String, Float> einheit;

    public Food(String name, float kcal, float kohlenhydrate, float proteine, float fett) {
        this.name = name;
        this.kcal = kcal;
        this.kohlenhydrate = kohlenhydrate;
        this.proteine = proteine;
        this.fett = fett;
    }

    public int getId(){
        return this.id;
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

    public void setId (int id){
        this.id = id;
    }

    public void setName (String name){
        this.name = name;
    }

    public void setKcal (Float kcal){
        this.kcal = kcal;
    }

    public void setKohlenhydrate (Float kohlenhydrate){
        this.kohlenhydrate = kohlenhydrate;
    }

    public void setProteine (Float proteine){
        this.proteine = proteine;
    }

    public void setFett (Float fett){
        this.fett = fett;
    }

    public String toString(){
        return "Food [id=" + id + ", name=" + name + ", kcal=" + kcal + ", kohlenhydrate=" + kohlenhydrate + ", proteine=" + proteine + ", fett=" + fett;
    }

}
