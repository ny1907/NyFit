package ny.nyfit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

/**
 * Created by U820319 on 28.11.2016.
 */

public class StatisticsArrayAdapter extends ArrayAdapter<String> {
    private final Context context;
    /*private final String[] values;*/
    private final ArrayList<String> values;

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


        tDatum.setText(values.get(position));
        tGewicht.setText(values.get(position+1));
        tFett.setText(values.get(position+2));
        tMuskeln.setText(values.get(position+3));
        tWasser.setText(values.get(position+4));

        return rowView;
    }
}
