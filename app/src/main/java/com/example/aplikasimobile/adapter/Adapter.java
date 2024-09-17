package com.example.aplikasimobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aplikasimobile.R;
import com.example.aplikasimobile.model.Data;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    // Constructor
    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
        this.inflater = LayoutInflater.from(activity); // Initialize inflater here
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int location) {
        return items.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_row, parent, false); // Inflate the view
        }

        TextView id = convertView.findViewById(R.id.id);
        TextView number = convertView.findViewById(R.id.number);
        TextView name = convertView.findViewById(R.id.name);
        TextView birth = convertView.findViewById(R.id.birth);
        TextView gender = convertView.findViewById(R.id.gender);
        TextView address = convertView.findViewById(R.id.address);

        Data data = items.get(position);

        id.setText(data.getId());
        number.setText(data.getNumber());
        name.setText(data.getName());
        birth.setText(data.getBirth());
        gender.setText(data.getGender());
        address.setText(data.getAddress());

        return convertView;
    }
}
