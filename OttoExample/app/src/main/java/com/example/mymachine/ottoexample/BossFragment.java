package com.example.mymachine.ottoexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;


public class BossFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_boss, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OttoBusStation.getBus().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        OttoBusStation.getBus().unregister(this);
    }

    @Subscribe
    public void receiveOttoMessage(OttoMessage ottoMessage){

        Log.i("tagThis", "so this is the message in fragmentUserHome: "+ ottoMessage.getMsg());

    }

}
