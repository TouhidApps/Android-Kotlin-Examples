package com.touhidapps.retrofitexample.networking;

import com.touhidapps.retrofitexample.model.MyFlower;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET(AllApiUrl.GET_FLOWER_LIST)
    Call<List<MyFlower>> getFlowerList(@Query("option") String option); // ?option=3

//    @POST(AllApiUrl.POST_CASHOUT_REQUEST)
//    Call<String> getGiftList(@Body Map<String, Integer> catId);
//
//    @GET(AllApiUrl.POST_CASHOUT_REQUEST) // api/CastOutGift/GiftCashBalance?userid=3262
//    Call<String> getGiftCashBalance(@Query("userid") String userid);
//
//    @Multipart
//    @POST(AllApiUrl.POST_CASHOUT_REQUEST)
//    Call<String> uploadVideoFile(@Part() List<MultipartBody.Part> requestBody);


}
