package com.touhidapps.retrofitexample.networking;

import android.util.Log;

import com.touhidapps.retrofitexample.model.MyFlower;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyApiCall {

    private static MyApiCall myApiCall;
    private ApiInterface apiInterface;

    public MyApiCall() {
        apiInterface = RetrofitClient.client().create(ApiInterface.class);
    }

    public static MyApiCall instance() {
        if (myApiCall == null) {
            myApiCall = new MyApiCall();
        }
        return myApiCall;
    } // instance

    public void getFlowerList(final Req<List<MyFlower>> req) {

        apiInterface.getFlowerList("3").enqueue(new Callback<List<MyFlower>>() {
            @Override
            public void onResponse(Call<List<MyFlower>> call, Response<List<MyFlower>> response) {
                req.done(response);
            }

            @Override
            public void onFailure(Call<List<MyFlower>> call, Throwable t) {
                req.error();
            }
        });

    } // getData

    public void uploadFile(String filePath, String title, String des,
                           final ProgressStatus progressStatus, final Req req) {

        File file = new File(filePath);

        final ProgressRequestBody requestVideoFile = new ProgressRequestBody(file, new ProgressRequestBody.UploadCallbacks() {
            @Override
            public void onProgressUpdate(int percentage) {
                progressStatus.progress(percentage);
            }

            @Override
            public void onError() {
                progressStatus.error();
            }

            @Override
            public void onFinish() {
                progressStatus.finish();
            }
        });

        MultipartBody.Part videoPart = MultipartBody.Part.createFormData("video", file.getName(), requestVideoFile);

        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(videoPart)
                .addFormDataPart("title", title)
                .addFormDataPart("myDescription", des)
                .build();

//        apiInterface.uploadVideoFile(requestBody.parts()).enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                req.done(response);
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                req.error();
//            }
//        });


    }


}
