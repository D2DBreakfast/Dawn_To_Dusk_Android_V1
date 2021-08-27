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
    @Headers("Content-Type:application/json")
    @FormUrlEncoded
    @POST("userRegister")
    fun userRegistration(
            @Field("fullName") name: String,
            @Field("email") email: String,
            @Field("countryCode") country_code:String,
            @Field("mobileNo") mobilenumber: String
        ): Call<ResponseBody>


         @GET("movielist.json")
         fun getAllFoodCategoryName(): Call<List<FoodCategory>>

         @GET("movielist.json")
         fun getAllFoodShortDesc() : Call<List<FoodShortDesc>>

         /*Home Screen Food Short Description List*/
         @GET("users?page=2")
         fun getAllFoodShortDescDetailsList(): Call<FoodShortDescList>

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