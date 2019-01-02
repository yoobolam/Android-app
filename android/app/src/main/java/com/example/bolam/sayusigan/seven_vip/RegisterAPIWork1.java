package com.example.bolam.sayusigan.seven_vip;

import com.example.bolam.sayusigan.ImeiData;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by bolam on 2018-05-19.
 */

public interface RegisterAPIWork1 {
    @FormUrlEncoded
    @POST("/vwork1.php")
    public void insertVip1(
            @Field( "imei") String imei, @Field( "store") String store ,Callback<Response> callback);
}
