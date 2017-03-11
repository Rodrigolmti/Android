package net.fumapp_android.service;

import net.fumapp_android.model.TO.TONote;
import net.fumapp_android.model.TO.TOSchedule;
import net.fumapp_android.model.TO.TOStudent;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Rodrigo Lopes Martins on 29/01/17.
 */

public interface SinAppService {

    @FormUrlEncoded
    @POST("crawler/attempt")
    Call<TOStudent> attempt(
            @Field("registro_academico") String username,
            @Field("senha") String password);

    @FormUrlEncoded
    @POST("crawler/points/")
    Call<TONote> points(
            @Field("registro_academico") String username,
            @Field("senha") String password);

    @FormUrlEncoded
    @POST("crawler/schedules")
    Call<TOSchedule> schedule(
            @Field("registro_academico") String username,
            @Field("senha") String password);

    @GET("crawler/logout")
    Call<TOStudent> logout();
}
