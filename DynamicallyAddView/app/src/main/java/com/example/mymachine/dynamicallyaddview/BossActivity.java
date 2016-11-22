package com.example.mymachine.dynamicallyaddview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BossActivity extends AppCompatActivity {

    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);

        Button btn_add;

        btn_add= (Button)findViewById(R.id.btn_add);
        container = (LinearLayout)findViewById(R.id.linear_container);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater layoutInflater =
                        (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row_goon, null);

                Button btn_remove= (Button)addView.findViewById(R.id.btn_remove);
                btn_remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((LinearLayout)addView.getParent()).removeView(addView);
                    }
                });

                container.addView(addView);

            }
        });




    }
}
