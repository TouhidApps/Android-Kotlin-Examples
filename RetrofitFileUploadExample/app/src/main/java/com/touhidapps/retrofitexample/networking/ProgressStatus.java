package com.touhidapps.retrofitexample.networking;

public interface ProgressStatus {

    void progress(int percentage);
    void error();
    void finish();

}
