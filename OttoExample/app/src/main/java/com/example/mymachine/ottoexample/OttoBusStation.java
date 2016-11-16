package com.example.mymachine.ottoexample;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by myMachine on 11/15/2016.
 */

public class OttoBusStation {

    private static final Bus bus= new Bus(ThreadEnforcer.MAIN);

    public static Bus getBus(){
        return bus;
    }

    private OttoBusStation(){

    }




}
