package com.touhidapps.retrofitexample.networking;

import retrofit2.Response;

public interface Req<T> {
    void done(Response<T> response);

    void error();
}