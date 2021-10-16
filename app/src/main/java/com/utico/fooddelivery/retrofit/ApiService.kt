package com.utico.fooddelivery.retrofit

import com.utico.fooddelivery.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    /*Registration ApiService Call*/
    //@Headers("Content-Type:application/json")
    @FormUrlEncoded
    @POST("register")
    fun userRegistration(
        @Field("countryCode") countryCode:String,
        @Field("fullName") fullName: String,
        @Field("mobileNo") mobileNumber: String,
        @Field("email") email: String,
        ): Call<UserRegistrationResponseModel>

    /*OTPVerify Method*/
   // @Headers("api_key:JPcopEq16fyQGjnzY3QXVDnGDZrgQAs1")
    @FormUrlEncoded
    @POST("verifyOtp")
       fun otpVerfication(
        @Field("countryCode") countryCode: String,
        @Field("mobileNo") mobileNumber: String,
        @Field("mobileOtp") mobileOtp:String
       ): Call<ResponseBody>

    /*Login ApiService Call*/
   // @Headers("api_key:JPcopEq16fyQGjnzY3QXVDnGDZrgQAs1")
    @FormUrlEncoded
    @POST("userLogin")
    fun userLogin(
        @Field("countryCode") countryCode:String,
        @Field("mobileNo") mobileNumber: String
    ) : Call<ResponseBody>

    /*Getting the Food Sub Category Data Method*/
    @FormUrlEncoded
    @POST("admin/fetchSubCategory")
    fun getSubCategoryList(
        @Field("mainCategoryId") mainCategoryId:String
    ): Call<SubCategoryResponseModel>


    /*Home Screen Food Short Description List*/
         @FormUrlEncoded
         @POST("fetchMenuDetails")
         fun getMenuDetailsList(
          @Field("itemMainCategoryName") itemMainCategoryName:String
         ): Call<MenuDetailsResponseModel>

    /*Fetch the subCategory Menu details*/
     @FormUrlEncoded
     @POST("fetchSubCategoryMenusDetails")
     fun getSubCategoryMenusDetails(
        @Field("itemMainCategoryName") itemMainCategoryName:String,
        @Field("itemSubCategoryName") itemSubCategoryName:String,
        @Field("itemFoodType") itemFoodType:String,
        ): Call<SubCategoryMenuDetailsModel>

     /*Post addToCart Items data*/
       @FormUrlEncoded
       @POST("myCart")
       fun addToCart(
         @Field("itemMainCategoryName") itemMainCategoryName:String,
         @Field("itemSubCategoryName") itemSubCategoryName:String,
         @Field("itemFoodType") itemFoodType:String,
         @Field("itemName") itemName:String,
         @Field("itemId") itemId:String,
         @Field("itemQuantity") itemQuantity:String,
         @Field("itemPrice") itemPrice:String,
         @Field("userId") userId:String
    ): Call<ResponseBody>

    /*Fetch addToCart Items details*/
    @FormUrlEncoded
    @POST("fetchCartDetails")
    fun getAddToCartDetails(
        @Field("userId") userId:String
    ): Call<AddToCartDetailsResponseModel>


    /*Place Order*/
    @FormUrlEncoded
    @POST("Placeorder")
    fun placeOrder(
        @Field("itemMainCategoryName") itemMainCategoryName:String,
        @Field("itemSubCategoryName") itemSubCategoryName:String,
        @Field("itemFoodType") itemFoodType:String,
        @Field("itemName") itemName:String,
        @Field("itemId") itemId:String,
        @Field("itemQuantity") itemQuantity:String,
        @Field("itemPrice") itemPrice:String,
        @Field("userId") userId:String
    ): Call<OrderPlacedResponse>





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

         /*Get Profile*/
         @GET("users/2")
         fun getProfile(): Call<ProfileFakeApi>

         /*Post Profile Data*/
         @Headers("api_key:JPcopEq16fyQGjnzY3QXVDnGDZrgQAs1")
         @FormUrlEncoded
         @POST("updateUserDetails")
         fun EditUserProfile(
             @Field("mobile") mobileNumber: String,
             @Field("fullname") fullName:String,
             @Field("email") email:String
         ): Call<ResponseBody>

}