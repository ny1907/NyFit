package ny.nyfit;

import java.util.HashMap;

/**
 * Created by U820319 on 02.12.2016.
 */

public class Util {

    public static HashMap<String, String> getStatisticValues(String stat){
        String id;
        String gewicht;
        String fett;
        String muskel;
        String wasser;
        String datum;

        HashMap<String, String> map = new HashMap<>();

        String values = stat.substring(stat.indexOf("["), stat.indexOf("]") +1);
        id = values.substring(values.indexOf("id") +3, values.indexOf(", gewicht"));
        gewicht = values.substring(values.indexOf("gewicht") +8, values.indexOf(", fett"));
        fett = values.substring(values.indexOf("fett") +5, values.indexOf(", muskel"));
        muskel = values.substring(values.indexOf("muskel") +7, values.indexOf(", wasser"));
        wasser = values.substring(values.indexOf("wasser") +7, values.indexOf(", datum"));
        datum = values.substring(values.indexOf("datum") +6, values.indexOf("]"));

        map.put("id", id);
        map.put("gewicht", gewicht);
        map.put("fett", fett);
        map.put("muskel", muskel);
        map.put("wasser", wasser);
        map.put("datum", datum);

        return map;
    }
}
