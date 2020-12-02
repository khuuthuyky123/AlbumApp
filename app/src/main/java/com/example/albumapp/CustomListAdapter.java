//package com.example.albumapp;
//
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.GridView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class CustomListAdapter extends ArrayAdapter {
//    Context context;
//    Integer[][] thumbnails;
//    String[] name;
//    String[] date;
//    GridView gridview;
//
//    public CustomListAdapter(Context context, int layoutToBeInflated, Integer[][] thumbnails, String[] date) {
//        super(context, R.layout.grid_image, thumbnails);
//        this.context = context;
//        this.thumbnails = thumbnails;
//        this.date = date;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
//        View row = inflater.inflate(R.layout.grid_image, null);
//        TextView dates = (TextView) row.findViewById(R.id.date);
//        dates.setText(date[position]);
//        gridview = (GridView) row.findViewById(R.id.grid);
//        gridview.setAdapter(new CustomGridAdapter(this.context, thumbnails[position]));
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
//            }
//        });
//        return (row);
//    }
//
//}