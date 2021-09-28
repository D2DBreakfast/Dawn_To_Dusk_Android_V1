package com.utico.fooddelivery.retrofit

import com.utico.fooddelivery.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    /*Login ApiService Call*/
    @Headers("api_key:JPcopEq16fyQGjnzY3QXVDnGDZrgQAs1")
    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Field("email") email: String,
        @Field("countryCode") country_code:String,
        @Field("mobile") phone_number:String
    ) : Call<ResponseBody>


    /*Registration ApiService Call*/
    @Headers("api_key:JPcopEq16fyQGjnzY3QXVDnGDZrgQAs1")
    @FormUrlEncoded
    @POST("register")
    fun userRegistration(
            @Field("fullname") name: String,
            @Field("email") email: String,
            @Field("countryCode") country_code:String,
            @Field("mobile") mobilenumber: String
        ): Call<ResponseBody>

    /*OTPVerify Method*/
    @Headers("api_key:JPcopEq16fyQGjnzY3QXVDnGDZrgQAs1")
    @FormUrlEncoded
    @POST("varifyOtp")
       fun otpVerfication(
        @Field("code") otp:String,
        @Field("mobile") mobilenumber: String,
        @Field("countryCode") countryCode: String
       ): Call<ResponseBody>

         /*Home Screen Food Short Description List*/
         @GET("users?page=2")
         fun getAllFoodShortDescDetailsList(): Call<FoodShortDescList>

         /*Getting the Food Sub Category Data Method*/
         @GET("users?page=2")
         fun getAllFoodSubCategoryList(): Call<FoodSubCategoryList>

         /*Get Food Sub Category wise data*/
         @GET("users?")
         fun getFoodSubCategoryWiseList(@Query("page")FoodSubCategoryName:String): Call<FoodShortDescList>

         /*Get Food Search*/
         @GET("users?")
         fun getHomeFoodSearchList(@Query("page")foodSearchText:String): Call<FoodShortDescList>

         /*Meals Plan Method*/
          @GET("users?page=2")
          fun getAllMealPlanList(): Call<MealPlanList>

         /*Get Food SearchList*/
         @GET("users?page=2")
         fun searchFoodList(): Call<FoodList>

         /*Get Notifications*/
         @GET("users?page=2")
         fun getAllNotification(): Call<NotificationList>


}