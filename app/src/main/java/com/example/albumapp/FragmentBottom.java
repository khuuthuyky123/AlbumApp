package com.example.albumapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

public class FragmentBottom extends Fragment implements FragmentCallbacks {
    MainActivity main;
    Context context = null;
    Button left, right;
    int layout_resource;

    public static FragmentBottom newInstance(int Arg1) {
        FragmentBottom fragment = new FragmentBottom();
        Bundle bundle = new Bundle();
        bundle.putInt("arg1", Arg1);
        fragment.setArguments(bundle);
        return fragment;
    }// newInstance

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity();
            main = (MainActivity) getActivity();
            layout_resource = getArguments().getInt("arg1", R.layout.bottom_fragment_all_album);
        } catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout view_layout_bottom = (LinearLayout) inflater.inflate(layout_resource, null);
        if (layout_resource == R.layout.bottom_fragment) {
            left = (Button) view_layout_bottom.findViewById(R.id.anhCamera);
            left.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(), "1111", Toast.LENGTH_SHORT).show();
                }
            });
            right = (Button) view_layout_bottom.findViewById(R.id.tatCaAlbum);
            right.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(), "2222", Toast.LENGTH_SHORT).show();
                    main.onMsgFromFragToMain("BOTTOM-FRAG", String.valueOf(R.layout.bottom_fragment_all_album));
                    main.onMsgFromFragToMain("TOP-FRAG", String.valueOf(R.layout.top_fragment_all_album));
                    main.onMsgFromFragToMain("LIST-FRAG", String.valueOf(R.layout.list_fragment_all_album));
                }
            });
        }
        if (layout_resource == R.layout.bottom_fragment_all_album) {

        }
        return view_layout_bottom;
    }

    @Override
    public void onMsgFromMainToFragment(String strValue) {

    }
}
