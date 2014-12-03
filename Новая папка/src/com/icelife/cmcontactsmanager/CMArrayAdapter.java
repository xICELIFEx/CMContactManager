package com.icelife.cmcontactsmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icelife.clcontactslist.Database.CLContactModel;

/**
 * Created by ICELIFE on 29.10.2014.
 */
public class CMArrayAdapter extends ArrayAdapter {
    private final Context context;
    private final CMContactModel[] values;

    public CMArrayAdapter(Context context, CMContactModel[] values) {
        super(context, R.layout.contact_cell_layout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.contact_cell_layout, parent, false);
        TextView textViewFirstName = (TextView)rowView.findViewById(R.id.textViewFirstName);
        ImageView imageViewAvatar = (ImageView)rowView.findViewById(R.id.imageAvatar);
        imageViewAvatar.setImageResource(R.drawable.ic_launcher);
        textViewFirstName.setText(values[position].getFirstName());
        return rowView;
    }
}
