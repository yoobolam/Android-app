package com.example.bolam.sayusigan.two_select;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by bolam on 2018-05-09.
 */

public interface RegisterAPIsaju {
    @FormUrlEncoded
    @POST("/saju.php")
    public void insertUser2(
            @Field("imei") String imei,
            Callback<Response> callback);
}

