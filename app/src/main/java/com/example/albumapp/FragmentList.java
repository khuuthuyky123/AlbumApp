package com.example.albumapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.io.File;
import java.util.Vector;

public class FragmentList extends Fragment implements FragmentCallbacks {
    MainActivity main;
    GridView gridView;
    Context context = null;
    int layout_resource;
    //    Integer[] thumbnails = {R.drawable.pic01_small, R.drawable.pic02_small, R.drawable.pic03_small, R.drawable.pic04_small,
//            R.drawable.pic05_small, R.drawable.pic06_small, R.drawable.pic07_small, R.drawable.pic08_small};
    String path;

//
//    public static FragmentList newInstance(int arg1) {
//        FragmentList fragment = new FragmentList();
//        Bundle bundle = new Bundle();
//        bundle.putInt("arg1", arg1);
//        fragment.setArguments(bundle);
//        return fragment;
//    }// newInstance

    public static FragmentList newInstance(int arg1, String strArg1) {
        FragmentList fragment = new FragmentList();
        Bundle bundle = new Bundle();
        if (!strArg1.equals(" "))
            bundle.putString("strArg1", strArg1);
        bundle.putInt("arg1", arg1);
        fragment.setArguments(bundle);
        return fragment;
    }// newInstance

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException("Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity();
        context = getActivity();
        layout_resource = getArguments().getInt("arg1", R.layout.list_fragment_all_album);
        path = getArguments().getString("strArg1", "mnt/sdcard/DCIM/Camera");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout view_layout_main = (LinearLayout) inflater.inflate(layout_resource, null);
        if (layout_resource == R.layout.list_image) {
            gridView = view_layout_main.findViewById(R.id.grid_image_view);
            final CustomGridAdapter adapter = new CustomGridAdapter(context, path);
            gridView.setAdapter(adapter);
            gridView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    //main.onMsgFromFragToMain("BLUE-FRAG", String.valueOf(position));
                    String file = ((new File(path).listFiles())[position].getAbsolutePath());
                    main.onMsgFromFragToMain("LIST-FRAG", R.layout.single_image + "," + file);
                    main.onMsgFromFragToMain("BOTTOM-FRAG", R.layout.blank + ",1");
                    main.onMsgFromFragToMain("TOP-FRAG", String.valueOf(R.layout.blank));
                    main.onMsgFromFragToMain("NUM", "3");
                    //Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                }

            });
        }
        if (layout_resource == R.layout.list_fragment_all_album) {
            ListView listView = view_layout_main.findViewById(R.id.list_image_view);
            final Vector<File> name = new Vector<File>();
            File f = new File("mnt/sdcard/DCIM");
            Log.v("Files", f.isDirectory() + "");
            File[] files = f.listFiles();
            for (File inFile : files) {
                if (inFile.isDirectory() && inFile.canWrite() && inFile.canRead() && !inFile.isHidden()) {
                    // is   directory
                    name.add(inFile);
                }
            }
            f.delete();
            f = new File("mnt/sdcard/Pictures/");
            files = f.listFiles();
            for (File inFile : files) {
                if (inFile.isDirectory() && inFile.canWrite() && inFile.canRead() && !inFile.isHidden()) {
                    // is   directory
                    name.add(inFile);
                }
            }
            final CustomListAdapter adapter = new CustomListAdapter(context, layout_resource, name);
            listView.setAdapter(adapter);
            listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    main.onMsgFromFragToMain("LIST-FRAG", R.layout.list_image + "," + name.get(position).getAbsolutePath());
                    main.onMsgFromFragToMain("BOTTOM-FRAG", R.layout.blank + ",1");
                    main.onMsgFromFragToMain("TOP-FRAG", String.valueOf(R.layout.blank));
                    main.onMsgFromFragToMain("NUM", "3");
                    //Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                }

            });
        }
        if (layout_resource == R.layout.single_image) {
            ImageView imageView = view_layout_main.findViewById(R.id.singleImage);
            File f = new File(path);
            Bitmap myBitmap = BitmapFactory.decodeFile(f.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
        }
        return view_layout_main;
    }

    @Override
    public void onMsgFromMainToFragment(String strValue) {

    }
}