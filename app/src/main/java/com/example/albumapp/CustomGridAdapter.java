package com.example.albumapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;

public class CustomGridAdapter extends BaseAdapter {
    Integer[] smallImages; // thumbnail data set
    private Context context; // main activity’s context

    public CustomGridAdapter(Context mainActivityContext, Integer[] thumbnails) {
        context = mainActivityContext;
        smallImages = thumbnails;
    }

    // how many entries are there in the data set?
    public int getCount() {
        return smallImages.length;
    }

    // what is in a given 'position' in the data set?
    public Object getItem(int position) {
        return smallImages[position];
    }

    // what is the ID of data item in given 'position‘?
    public long getItemId(int position) {
        return position;
    }

    // create a view for each thumbnail in the data set, add it to gridview
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        CheckBox checkBox;

        // if possible, reuse (convertView) image already held in cache
        if (convertView == null) {

            // no previous version of thumbnail held in the scrapview holder define entry in res/values/dimens.xml for grid height,width in dips <dimen name="gridview_size">100dp</dimen> // setLayoutParams will do conversion to physical pixels
            imageView = new ImageView(context);
            int gridsize = context.getResources().getDimensionPixelOffset(R.dimen.gridview_size);
            imageView.setLayoutParams(new GridView.LayoutParams(gridsize, gridsize));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(5, 5, 5, 5);

        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(smallImages[position]);
        imageView.setId(position);
        return imageView;
    }//getView
}
