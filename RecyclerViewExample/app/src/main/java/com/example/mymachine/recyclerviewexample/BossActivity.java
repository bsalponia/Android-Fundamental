package com.example.mymachine.recyclerviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class BossActivity extends AppCompatActivity {

    private List<ApptDetails> detailsList= new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);


        recyclerView= (RecyclerView)findViewById(R.id.rcylerView);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter= new RcylerAdapter(detailsList);

        recyclerView.setAdapter(adapter);

        myApptData();

    }

    private void myApptData(){

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        String date = df.format(c.getTime());

        Date currentLocalTime = c.getTime();
        DateFormat datee = new SimpleDateFormat("KK:mm");
        datee.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String time = datee.format(currentLocalTime);


        ApptDetails apptDetails;

        for(int i=0; i<20;i++){
            apptDetails= new ApptDetails("Hotels & Spa", "New Delhi, India" , date, time);
            detailsList.add(apptDetails);
        }

    }


}
