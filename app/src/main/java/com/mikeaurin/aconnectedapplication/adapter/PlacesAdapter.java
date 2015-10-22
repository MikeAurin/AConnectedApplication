package com.mikeaurin.aconnectedapplication.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mikeaurin.aconnectedapplication.data.Places;

import java.util.ArrayList;

public class PlacesAdapter extends BaseAdapter {

    public static final int ID_CONSTANT = 0x10102020;

    Context mContext;
    ArrayList<Places> mPlaces;

    public PlacesAdapter(Context _c, ArrayList<Places> _places) {
        mContext = _c;
        mPlaces = _places;
    }

    @Override
    public int getCount() {
        if(mPlaces == null) {
            return 0;
        }
        return mPlaces.size();
    }

    @Override
    public Places getItem(int position) {
        if(mPlaces == null || position < 0 || position >= mPlaces.size()) {
            return null;
        }
        return mPlaces.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  ID_CONSTANT + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        Places places = getItem(position);

        if(places != null) {
            TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
            tv.setText(places.getName());

            tv = (TextView) convertView.findViewById(android.R.id.text2);
            tv.setText(places.getType());
        }

        return convertView;
    }
}
