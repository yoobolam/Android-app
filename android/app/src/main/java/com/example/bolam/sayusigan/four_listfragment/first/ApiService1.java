package com.example.bolam.sayusigan.four_listfragment.first;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by qsxcd on 2018-05-05.
 */

public interface ApiService1 {
    public static final String API_URL="http://52.78.230.83/connect/";

    @FormUrlEncoded
    @POST("list.php/")
    Call<ResponseBody>getCommentStr(@Field("imei") String imei);

    @FormUrlEncoded
    @POST("went.php/")
    Call<ResponseBody>getPostComment(@Field("imei") String imei);

    @FormUrlEncoded
    @POST("vip.php/")
    Call<ResponseBody>getPostCommentStr(@Field("imei") String imei);

    @FormUrlEncoded
    @POST("map.php/")
    Call<ResponseBody>getPostMap(@Field("imei") String imei);
}
