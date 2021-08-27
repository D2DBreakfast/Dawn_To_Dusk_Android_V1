package com.utico.fooddelivery.retrofit

import com.utico.fooddelivery.model.FoodCategory
import com.utico.fooddelivery.model.FoodShortDesc
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


         @GET("movielist.json")
         fun getAllFoodCategoryName(): Call<List<FoodCategory>>

         @GET("movielist.json")
         fun getAllFoodShortDesc() : Call<List<FoodShortDesc>>

}