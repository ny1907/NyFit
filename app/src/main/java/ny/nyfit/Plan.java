package ny.nyfit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by U820319 on 14.11.2016.
 */

public class Plan {
    private final int id;
    private List<Integer> liste;
    private String name;
    private float kalorienGesamt;
    private float kohlenhydrateGesamt;
    private float proteineGesamt;
    private float fettGesamt;


    public Plan(int id, String name){
        this.id = id;
        this.name = name;
        this.liste = new ArrayList();
    }

    public int getId(){
        return this.id;
    }

    public List<Integer> getFoodList(){
        return liste;
    }

    public String getName(){
        return name;
    }

}
