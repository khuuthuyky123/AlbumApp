package com.example.albumapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.Vector;

public class CustomListAdapter extends ArrayAdapter {
    Context context;
    //Integer[] thumbnail;
    Vector<File> name;
    //String[] date;

    public CustomListAdapter(Context context, int layoutToBeInflated, Vector<File> name) {
        super(context, R.layout.all_album_item, name);
        this.context = context;
        //this.thumbnail = thumbnails;
        this.name = name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.all_album_item, null);
        TextView names = row.findViewById(R.id.albumName);
        names.setText(name.get(position).getName());
        ImageView thumbnails = row.findViewById(R.id.albumThumbnail);
        thumbnails.setImageResource(R.drawable.folder);
        return (row);
    }

}