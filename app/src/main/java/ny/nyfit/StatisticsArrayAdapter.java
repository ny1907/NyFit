package ny.nyfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by U820319 on 28.11.2016.
 */

public class StatisticsArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    /*private final String[] values;*/
    private final ArrayList<String> values;
    private final Util util = new Util();

    public StatisticsArrayAdapter(Context context, ArrayList<String> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.activity_statistics_list_element, parent, false);
        TextView tDatum = (TextView) rowView.findViewById(R.id.listDatum);
        TextView tGewicht = (TextView) rowView.findViewById(R.id.listGewicht);
        TextView tFett = (TextView) rowView.findViewById(R.id.listFett);
        TextView tMuskeln = (TextView) rowView.findViewById(R.id.listMuskeln);
        TextView tWasser = (TextView) rowView.findViewById(R.id.listWasser);

        HashMap<String, String> map = util.getStatisticValues(values.get(position));

        tDatum.setText(map.get("datum"));
        tGewicht.setText(map.get("gewicht"));
        tFett.setText(map.get("fett"));
        tMuskeln.setText(map.get("muskel"));
        tWasser.setText(map.get("wasser"));

        return rowView;
    }
}
