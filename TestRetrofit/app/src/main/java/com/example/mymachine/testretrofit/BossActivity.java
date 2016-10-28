package com.example.mymachine.testretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mymachine.testretrofit.Model.ParlorUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.mymachine.testretrofit.MyInterface.BASE_URL;

public class BossActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);

//        final TextView textView= (TextView)findViewById(R.id.txtName);

        Button button= (Button)findViewById(R.id.btnGetData);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit= new Retrofit.Builder().baseUrl(BASE_URL).
                        addConverterFactory(GsonConverterFactory.create()).build();

                MyInterface myInterface= retrofit.create(MyInterface.class);
                Call<ParlorUser> requestData= myInterface.getSuccessDetails();
                requestData.enqueue(new Callback<ParlorUser>() {
                    @Override
                    public void onResponse(Call<ParlorUser> call, Response<ParlorUser> response) {

                        Log.i("tagThis", "Response string: " +response.toString());

                        ParlorUser parlorUser= response.body();
                        Log.i("tagThis", "parlorUser: "+ parlorUser.getSuccess());
                        Log.i("tagThis", "Name: "+ parlorUser.getData().getName()+ " AccessToken: "+
                        parlorUser.getData().getAccessToken());


                    }

                    @Override
                    public void onFailure(Call<ParlorUser> call, Throwable t) {

                        Log.i("tagThis", "Failure: "+ t.getMessage());

                    }
                });

            }
        });

    }
}
