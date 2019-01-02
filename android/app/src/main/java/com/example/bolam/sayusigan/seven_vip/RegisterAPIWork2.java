package com.example.bolam.sayusigan.seven_vip;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by bolam on 2018-05-19.
 */

public interface RegisterAPIWork2 {
    @FormUrlEncoded
    @POST("/vwork2.php")
    public void insertVip2(
            @Field("imei") String imei,@Field( "store") String store ,Callback<Response> callback);
}