package com.example.mymachine.viewpagerexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by myMachine on 10/26/2016.
 */

public class MyFragment1 extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup= (ViewGroup)inflater.inflate(R.layout.fragment_file1, container, false);

        return viewGroup;

    }






}
