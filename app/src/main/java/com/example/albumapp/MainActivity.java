package com.example.albumapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.Vector;

public class MainActivity extends FragmentActivity implements MainCallbacks {
    FragmentTransaction ft;
    FragmentTop topFragment;
    FragmentBottom bottomFragment;
    FragmentList listFragment;
    Vector<Integer> numOfFragChanged = new Vector<Integer>();

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case 2: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission denied to write your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActivityCompat.requestPermissions(MainActivity.this,
//                new String[]{Manifest.permission.MANAGE_EXTERNAL_STORAGE},
//                1);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                2);
        setContentView(R.layout.activity_main);

        ft = getSupportFragmentManager().beginTransaction();
        topFragment = FragmentTop.newInstance(R.layout.top_fragment);
        ft.replace(R.id.holder_top_layout, topFragment);
        ft.commit();

        ft = getSupportFragmentManager().beginTransaction();
        bottomFragment = FragmentBottom.newInstance(R.layout.bottom_fragment, 0);
        ft.replace(R.id.holder_bottom_layout, bottomFragment);
        ft.commit();

        ft = getSupportFragmentManager().beginTransaction();
        listFragment = FragmentList.newInstance(R.layout.list_image, " ");
        ft.replace(R.id.holder_list_layout, listFragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        for (int i = 0; i < numOfFragChanged.lastElement(); i++)
            getSupportFragmentManager().popBackStack();
        numOfFragChanged.remove(numOfFragChanged.size() - 1);
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
                ft.addToBackStack("T1");
                ft.commit();
                //getSupportFragmentManager().executePendingTransactions();
            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain" + e.getMessage());
            }
        }
        if (sender.equals("BOTTOM-FRAG")) {
            try {
                String[] tokens = strValue.split(",");
                //bottomFragment.onMsgFromMainToFragment(strValue);
                ft = getSupportFragmentManager().beginTransaction();
                bottomFragment = FragmentBottom.newInstance(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
                ft.replace(R.id.holder_bottom_layout, bottomFragment, "BOTTOM-TAG");
                ft.addToBackStack("B1");
                ft.commit();
                //getSupportFragmentManager().executePendingTransactions();

            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain" + e.getMessage());
            }
        }
        if (sender.equals("LIST-FRAG")) {
            try {
                String[] tokens = strValue.split(",");
                //listFragment.onMsgFromMainToFragment(strValue);
                ft = getSupportFragmentManager().beginTransaction();
                listFragment = FragmentList.newInstance(Integer.parseInt(tokens[0]), tokens[1]);
                ft.replace(R.id.holder_list_layout, listFragment, "LIST-TAG");
                ft.addToBackStack("L1");
                ft.commit();
                //getSupportFragmentManager().executePendingTransactions();

            } catch (Exception e) {
                Log.e("ERROR", "onStrFromFragToMain" + e.getMessage());
            }
        }
        if (sender.equals("NUM")) {
            numOfFragChanged.add(Integer.parseInt(strValue));
        }
    }

}