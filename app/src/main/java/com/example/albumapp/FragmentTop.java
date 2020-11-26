package com.example.albumapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class FragmentTop extends Fragment implements FragmentCallbacks {
    MainActivity main;
    Context context = null;
    Button left, right;

    public static FragmentTop newInstance(String strArg) {
        FragmentTop fragment = new FragmentTop();
        Bundle args = new Bundle();
        args.putString("strArg1", strArg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity();
            main = (MainActivity) getActivity();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout view_layout_top = (LinearLayout) inflater.inflate(R.layout.top_fragment, null);
        left = (Button) view_layout_top.findViewById(R.id.leftbutton);
        left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "aaaaa", Toast.LENGTH_SHORT).show();
            }
        });
        right = (Button) view_layout_top.findViewById(R.id.rightbutton);
        right.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "bbbb", Toast.LENGTH_SHORT).show();
            }
        });
        return view_layout_top;
    }

    @Override
    public void onMsgFromMainToFragment(String strValue) {

    }
}