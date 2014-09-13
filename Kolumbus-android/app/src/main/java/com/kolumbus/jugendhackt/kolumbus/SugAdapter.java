package com.kolumbus.jugendhackt.kolumbus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rico on 13.09.2014.
 */
public class SugAdapter extends ArrayAdapter<Sug>{

    Context context;
    int layoutResourceId;
    Sug data[] = null;

    public SugAdapter(Context context, int layoutResourceId,Sug[] data){
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        SugHolder holder = null;

        if (row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);

            holder = new SugHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            holder.txtText = (TextView)row.findViewById(R.id.txttext);

            row.setTag(holder);
        }
        else{
            holder = (SugHolder)row.getTag();
        }

        Sug sug = data[position];
        holder.textTitle.setTex(sug.title);
        holder.imgIcon.setImageResource(sug.icon);

        return row;
    }

    static class  WeatherHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
        TextView txttext;
    }
}
