package com.heartsun.informer;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Bijay on 11/20/2019.
 */

public interface RetrofitApiInterface {

    @GET("msg_api.php")
    Call<List<Message_model>>getMessage(@Query("ReceiverMobileNo") String ReceiverMobileNo);

    @POST("login.php")
    Call<ResponseBody> login(@Body RequestBody register);

    @POST("register_api.php")
    Call<ResponseBody> register(@Body RequestBody register);

}
