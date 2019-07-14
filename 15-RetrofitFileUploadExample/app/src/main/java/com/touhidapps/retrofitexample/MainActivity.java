package com.touhidapps.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.touhidapps.retrofitexample.model.MyFlower;
import com.touhidapps.retrofitexample.networking.MyApiCall;
import com.touhidapps.retrofitexample.networking.ProgressStatus;
import com.touhidapps.retrofitexample.networking.Req;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadFile();

            }
        });

        findViewById(R.id.buttonCallApi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callApi();

            }
        });

    } // onCreate


    List<MyFlower> flowers = new ArrayList<>();

    private void callApi() {
        // Request data with api call
        MyApiCall.instance().getFlowerList(new Req<List<MyFlower>>() {
            @Override
            public void done(Response<List<MyFlower>> response) {
                flowers.clear();
                flowers.addAll(response.body());
                for (MyFlower f : flowers) {
                    Log.d(TAG, "done: " + f.fileName);
                }
            }

            @Override
            public void error() {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    } // callApi

    private void uploadFile() {
        MyApiCall.instance().uploadFile(
                "",
                "",
                "",
                new ProgressStatus() {
                    @Override
                    public void progress(int percentage) {

                    }

                    @Override
                    public void error() {

                    }

                    @Override
                    public void finish() {

                    }
                },
                new Req() {
                    @Override
                    public void done(Response response) {

                    }

                    @Override
                    public void error() {

                    }
                }
        );
    } // uploadFile


}
