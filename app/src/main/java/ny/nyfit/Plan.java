package ny.nyfit;

/**
 * Created by U820319 on 14.11.2016.
 */

public class Plan {
    private int id;
    private String name;
    private String datum;
    private float kalorienGesamt;
    private float kohlenhydrateGesamt;
    private float proteineGesamt;
    private float fettGesamt;


    public Plan(String name){
        this.name = name;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public String getDatum(){
        return this.datum;
    }

    public void setDatum(){

    }

    public String toString(){
        return "Plan[id=" + id + ", name=" + name + "]";
    }
}
