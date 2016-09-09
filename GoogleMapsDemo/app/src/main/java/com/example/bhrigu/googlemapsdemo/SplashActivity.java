package com.example.bhrigu.googlemapsdemo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.ViewGroup;



/**
 * Created by bhrigu on 9/7/16.
 */
public class SplashActivity extends Activity {


    @Override
    protected void onResume() {
        super.onResume();

        gpsSetting();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       gpsSetting();
    }

    public void gpsSetting(){

        LocationManager manager= (LocationManager) getSystemService(LOCATION_SERVICE);
        if(manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);
        }else{
            noGpsDialog();
        }

    }

    private void noGpsDialog(){

        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        LayoutInflater inflater= this.getLayoutInflater();
        ViewGroup view = (ViewGroup) findViewById(android.R.id.content);

        builder.setView(inflater.inflate(R.layout.dialog_location, view, false));
        builder.setPositiveButton(R.string.dialogYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openLocationSetting();
            }
        });
        builder.setNegativeButton(R.string.dialogNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finishAffinity();
            }
        });

        AlertDialog alertDialog= builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    private void openLocationSetting(){
        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(settingsIntent);
    }



}
