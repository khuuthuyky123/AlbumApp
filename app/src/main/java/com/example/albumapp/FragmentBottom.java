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
    int numOfFragChanged;
    int default_color = 0xffaaaaaa;
    int state;

    public static FragmentBottom newInstance(int Arg1, int Arg2) {
        FragmentBottom fragment = new FragmentBottom();
        Bundle bundle = new Bundle();
        bundle.putInt("arg1", Arg1);
        bundle.putInt("arg2", Arg2);
        fragment.setArguments(bundle);
        return fragment;
    }// newInstance

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity();
            main = (MainActivity) getActivity();
            layout_resource = getArguments().getInt("arg1", R.layout.bottom_fragment);
            state = getArguments().getInt("arg2", 0);

        } catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout view_layout_bottom = (LinearLayout) inflater.inflate(layout_resource, null);
        if (layout_resource == R.layout.bottom_fragment) {
            left = view_layout_bottom.findViewById(R.id.anhCamera);
            right = view_layout_bottom.findViewById(R.id.tatCaAlbum);
            if (state == 0) {
                right.setTextColor(default_color);
                left.setTextColor(0xff0099cc);
                left.setEnabled(false);
                right.setEnabled(true);
            } else {
                left.setEnabled(true);
                left.setTextColor(default_color);
                right.setTextColor(0xff0099cc);
                right.setEnabled(false);
            }
            left.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(), "1111", Toast.LENGTH_SHORT).show();
                    main.onMsgFromFragToMain("TOP-FRAG", String.valueOf(R.layout.top_fragment));
                    main.onMsgFromFragToMain("LIST-FRAG", R.layout.list_image + ", ");
                    main.onMsgFromFragToMain("BOTTOM-FRAG", R.layout.bottom_fragment + ",0");
                    main.onMsgFromFragToMain("NUM", "3");
                }
            });

            right.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(), "2222", Toast.LENGTH_SHORT).show();
                    //main.onMsgFromFragToMain("BOTTOM-FRAG", String.valueOf(R.layout.bottom_fragment_all_album));

                    main.onMsgFromFragToMain("TOP-FRAG", String.valueOf(R.layout.top_fragment_all_album));
                    main.onMsgFromFragToMain("LIST-FRAG", R.layout.list_fragment_all_album + ", ");
                    main.onMsgFromFragToMain("BOTTOM-FRAG", R.layout.bottom_fragment + ",1");
                    //numOfFragChanged = 3;
                    main.onMsgFromFragToMain("NUM", "3");
                }
            });
        }
        return view_layout_bottom;
    }

    @Override
    public void onMsgFromMainToFragment(String strValue) {

    }

    @Override
    public void onStop() {
        super.onStop();

        //main.onBackPressed();
    }
}
