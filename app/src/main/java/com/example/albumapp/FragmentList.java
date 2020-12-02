package com.example.albumapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class FragmentList extends Fragment implements FragmentCallbacks {
    MainActivity main;
    GridView gridView;
    Context context = null;
    Integer[] thumbnails = {R.drawable.pic01_small, R.drawable.pic02_small, R.drawable.pic03_small, R.drawable.pic04_small,
            R.drawable.pic05_small, R.drawable.pic06_small, R.drawable.pic07_small, R.drawable.pic08_small};
    String[] dates = {"a", "b", "c", "d", "e", "f", "g", "h"};

    public static FragmentList newInstance(String strArg1) {
        FragmentList fragment = new FragmentList();
        Bundle bundle = new Bundle();
        bundle.putString("arg1", strArg1);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout view_layout_main = (LinearLayout) inflater.inflate(R.layout.list_image, null);
        gridView = view_layout_main.findViewById(R.id.grid_image_view);


        final CustomGridAdapter adapter = new CustomGridAdapter(context, thumbnails);
        gridView.setAdapter(adapter);
        gridView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                main.onMsgFromFragToMain("BLUE-FRAG", String.valueOf(position));
                Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
            }

        });
        return view_layout_main;
    }

    @Override
    public void onMsgFromMainToFragment(String strValue) {

    }
}