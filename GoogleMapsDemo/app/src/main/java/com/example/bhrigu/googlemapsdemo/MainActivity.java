package com.example.bhrigu.googlemapsdemo;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            return;
        }

        FragmentMaps fragmentMaps= new FragmentMaps();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragmentMaps).commit();

    }

}
