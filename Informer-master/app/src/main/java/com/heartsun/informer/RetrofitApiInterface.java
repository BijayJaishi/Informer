package com.heartsun.informer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bijay on 11/20/2019.
 */

public interface RetrofitApiInterface {

    @GET("msg_api.php")
    Call<List<Message_model>>getMessage(@Query("ReceiverMobileNo") String ReceiverMobileNo);
//
//    @POST("login.php")
//    Call<List<LoginModel>>getlogin (@Body JSONObject login);

}
