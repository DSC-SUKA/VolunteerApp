package com.dsc.suka.volunteerapp.service;

import com.dsc.suka.volunteerapp.model.ResponseAudioAll;
import com.dsc.suka.volunteerapp.model.ContributionModel;
import com.dsc.suka.volunteerapp.model.ResponseLogin;
import com.dsc.suka.volunteerapp.model.RequestModel;
import com.dsc.suka.volunteerapp.model.RequestResponse;
import com.dsc.suka.volunteerapp.model.RespUploadResponse;
import com.dsc.suka.volunteerapp.model.ResponseSignup;
import com.dsc.suka.volunteerapp.model.ResponseImageUpload;
import com.dsc.suka.volunteerapp.model.ResponseUserData;
import com.dsc.suka.volunteerapp.model.ResponseRequestAll;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

//    @GET("/request")
//    Call<RequestResponse> requestedBook();
//    @GET("my_contribution")
//    Call<RequestModel> getContribution();
//    @GET("latest_contribution")
//    Call<ContributionModel> getLatestContribution();

    @GET("/request")
    Call<ResponseRequestAll> getAllRequests();

    @GET("/response")
    Call<ResponseAudioAll> getAllResponses();

    //Main Feature, not avalaible on API.
    @GET("/response/vol/{uid}")
    Call<ResponseAudioAll> getUserContribution(@Path("uid") String userId);

    @GET("user/{uid}")
    Call<ResponseUserData> getUserData(@Path("uid") String userId);

    @FormUrlEncoded
    @POST("/login")
    Call<ResponseLogin> login(@Field("email") String email,
                              @Field("password") String password);

    @FormUrlEncoded
    @POST("/createuser")
    Call<ResponseSignup> signup(@Field("email") String email,
                                @Field("password") String password,
                                @Field("phone") String phone_number,
                                @Field("name") String name,
                                @Field("image_url") String image_url,
                                @Field("role") String role);

    @Multipart
    @POST("/image/upload")
    Call<ResponseImageUpload> uploadImage(@Part MultipartBody.Part image);

    @Multipart
    @POST("response/upload")
    Call<RespUploadResponse> responseUpload(@Part MultipartBody.Part audio,
                                            @Part ("audio_id") RequestBody audioReqId,
                                            @Part ("category") RequestBody category,
                                            @Part ("user_id") RequestBody user_id,
                                            @Part ("user_image_url") RequestBody user_image_url,
                                            @Part ("name") RequestBody name,
                                            @Part ("dif_user_id") RequestBody dif_user_id,
                                            @Part ("dif_name") RequestBody dif_name);



}
