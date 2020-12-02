package com.example.albumapp;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends FragmentActivity implements MainCallbacks {
    FragmentTransaction ft;
    FragmentTop topFragment;
    FragmentBottom bottomFragment;
    FragmentList listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft = getSupportFragmentManager().beginTransaction();
        topFragment = FragmentTop.newInstance(R.layout.top_fragment);
        ft.replace(R.id.holder_top_layout, topFragment);
        ft.commit();

        ft = getSupportFragmentManager().beginTransaction();
        bottomFragment = FragmentBottom.newInstance(R.layout.bottom_fragment);
        ft.replace(R.id.holder_bottom_layout, bottomFragment);
        ft.commit();

        ft = getSupportFragmentManager().beginTransaction();
        listFragment = FragmentList.newInstance(" ");
        ft.replace(R.id.holder_list_layout, listFragment);
        ft.commit();
    }

    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {
        //Toast.makeText(getApplication(), "MAIN GOT>>"+ sender + "\n"+ strValue , Toast.LENGTH_LONG ).show();
        if (sender.equals("TOP-FRAG")) {
            try {
                //topFragment.onMsgFromMainToFragment(strValue);

                ft = getSupportFragmentManager().beginTransaction();
                topFragment = FragmentTop.newInstance(Integer.parseInt(strValue));
                ft.replace(R.id.holder_top_layout, topFragment, "TOP-TAG");
                ft.commit();
                ft.addToBackStack("T1");
                getSupportFragmentManager().executePendingTransactions();
            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain" + e.getMessage());
            }
        }
        if (sender.equals("BOTTOM-FRAG")) {
            try {
                //bottomFragment.onMsgFromMainToFragment(strValue);
                ft = getSupportFragmentManager().beginTransaction();
                bottomFragment = FragmentBottom.newInstance(Integer.parseInt(strValue));
                ft.replace(R.id.holder_bottom_layout, bottomFragment, "BOTTOM-TAG");
                ft.commit();
                ft.addToBackStack("B1");
                getSupportFragmentManager().executePendingTransactions();


            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain" + e.getMessage());
            }
        }
        if (sender.equals("LIST-FRAG")) {
            try {
                listFragment.onMsgFromMainToFragment(strValue);
            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain" + e.getMessage());
            }
        }
    }
}