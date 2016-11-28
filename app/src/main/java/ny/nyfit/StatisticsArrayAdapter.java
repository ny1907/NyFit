package ny.nyfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by U820319 on 28.11.2016.
 */

public class StatisticsArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public StatisticsArrayAdapter(Context context, String[] values) {
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

        tDatum.setText(values[0]);
        tGewicht.setText(values[1]);
        tFett.setText(values[2]);
        tMuskeln.setText(values[3]);
        tWasser.setText(values[4]);

        return rowView;
    }
}
