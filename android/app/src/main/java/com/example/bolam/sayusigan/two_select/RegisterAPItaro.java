package com.example.bolam.sayusigan.two_select;

/**
 * Created by bolam on 2018-05-09.
 */

import android.util.Log;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Belal on 11/5/2015.
 */
public interface RegisterAPItaro {
    @FormUrlEncoded
    @POST("/taro.php")
    public void insertUser(
            @Field("imei") String imei,
             Callback<Response> callback);
    }


