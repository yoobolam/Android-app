package com.example.bolam.sayusigan.four_listfragment.first;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by bolam on 2018-05-20.
 */

public interface RegisterAPIDelete {
        @FormUrlEncoded
        @POST("/delete.php")
        public void insertDelete(
                @Field( "imei") String imei,@Field( "store") String store,@Field( "work") String work ,Callback<Response> callback);
    }
