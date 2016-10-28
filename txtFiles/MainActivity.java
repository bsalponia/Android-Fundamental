package com.bsalponia.flashlight.flashlightalerts;

//
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.telephony.TelephonyManager;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.ToggleButton;



public class MainActivity extends Activity {

    private static final String TAG= "tag.flashlight";


    private ToggleButton toggleButtonFlashlight, toggleButtonMessage, toggleButtonCall;

    private SeekBar seekBarBlinkSpeed;

    final int deviceSdkVersion= android.os.Build.VERSION.SDK_INT;
    private Boolean checkApi = (deviceSdkVersion < Build.VERSION_CODES.M);


    private CameraManager mCameraManager;
    private String mCameraId;
    private Camera camera= null;

    private BroadcastReceiver broadcastReceiverSms, broadcastReceiverCall;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*seekBarBlinkSpeed= (SeekBar)findViewById(R.id.seekBarSpeed);
        seekBarBlinkSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });*/

        toggleButtonFlashlight = (ToggleButton) findViewById(R.id.buttonFlashlight);
        toggleButtonFlashlight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if(isChecked){


                    Runnable runnable= new Runnable() {
                        @Override
                        public void run() {

                            if(!getCameraInstance()){

                                flashlightOn(checkApi);
                            }


                        }
                    };

                    Handler handler= new Handler();
                    handler.postDelayed(runnable, 0);

                }else{

                    Runnable runnable= new Runnable() {
                        @Override
                        public void run() {


                                flashlightOff(checkApi);


                        }
                    };

                    Handler handler= new Handler();
                    handler.postDelayed(runnable, 0);

                }



            }
        });
        toggleButtonCall= (ToggleButton)findViewById(R.id.buttonCall);
        toggleButtonCall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {


                IntentFilter intentFilter= new IntentFilter();
                intentFilter.addAction("android.intent.action.PHONE_STATE");
                intentFilter.addAction(Intent.ACTION_SCREEN_ON);
                intentFilter.addAction(Intent.ACTION_SCREEN_OFF);

                broadcastReceiverCall= new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {

                    }
                }

                if(isChecked){

                }else{

                }


            }
        });



        toggleButtonMessage= (ToggleButton)findViewById(R.id.buttonMessage);
        toggleButtonMessage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {

                    registerMessage();
                }else{

                    unRegisterMessage();
                }
            }
        });


    }

    private void registerMessage(){

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");

        broadcastReceiverSms = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (batteryLevel(context) < 15 || isScreenOn()) {
                    if (getCameraInstance()) {

                        flashlightOff(checkApi);
                    }
                }else{

                    flashlightOn(checkApi);
                }
            }
        };
        registerReceiver(broadcastReceiverSms, intentFilter);
    }

    private void unRegisterMessage(){

        unregisterReceiver(broadcastReceiverSms);
    }

    private boolean isScreenOn(){

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if(checkApi){
            return pm.isScreenOn();
        }
        return pm.isInteractive();
    }

    private int batteryLevel(Context context)
    {
        IntentFilter intentFilter= new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent intent  = context.registerReceiver(null, intentFilter);
        int    level   = 0;
        if (intent != null) {
            level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        }
        int    scale   = 0;
        if (intent != null) {
            scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 100);
        }
        return (level*100)/scale;
    }

    private boolean getCameraInstance(){

        camera= null;
        try{
            camera= Camera.open();
        }catch(Exception e){
            return true;
        }finally {
            if(camera!=null){
                camera.release();
            }
        }
        return false;

    }


    private void flashlightOn(Boolean deviceApi){


        if(deviceApi){

            camera= Camera.open();
            Camera.Parameters parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
            camera.startPreview();



        }else{

            mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

            try {
                mCameraId = mCameraManager.getCameraIdList()[0];
                mCameraManager.setTorchMode(mCameraId, true);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }

        }

    }

    private void flashlightOff(Boolean deviceApi){


        if(deviceApi){
            try{
                camera.stopPreview();
                camera.release();
            }catch (Exception e){
                e.printStackTrace();
            }



        }else{
            try {
                mCameraManager.setTorchMode(mCameraId, false);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }



    }

}