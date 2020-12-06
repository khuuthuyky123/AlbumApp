package com.example.albumapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.File;
import java.util.Vector;

public class CustomGridAdapter extends BaseAdapter {
    final Vector<String> name = new Vector<String>();
    final String[] imageExtension = {"jpg", "jpeg", "png", "gif", "WebP"};
    //Integer[] smallImages; // thumbnail data set
    String pathImage;
    private Context context; // main activity’s context

    public CustomGridAdapter(Context mainActivityContext, String path) {
        context = mainActivityContext;
        pathImage = path;

        File f = new File(path);
        File[] files = f.listFiles();
        for (File inFile : files) {
            if (inFile.isFile() && fn_endsWith(inFile.getName().toLowerCase())) {
                // is   directory
                name.add(inFile.getName());
            }
        }
    }

    boolean fn_endsWith(String name) {
        for (String ext : imageExtension) {
            if (name.endsWith(ext))
                return true;
        }
        return false;
    }

    // how many entries are there in the data set?
    public int getCount() {
        return name.size();
    }

    // what is in a given 'position' in the data set?
    public Object getItem(int position) {
        return name.get(position);
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
            //imageView.setPadding(5, 5, 5, 5);

        } else {
            imageView = (ImageView) convertView;
        }
        Bitmap myBitmap = BitmapFactory.decodeFile(pathImage + "/" + name.get(position));
        imageView.setImageBitmap(myBitmap);
        imageView.setBackgroundResource(R.drawable.back);
        imageView.setId(position);
        return imageView;
    }//getView
}
