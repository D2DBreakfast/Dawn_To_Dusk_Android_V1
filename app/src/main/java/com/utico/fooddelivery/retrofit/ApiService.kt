package com.utico.fooddelivery.retrofit

import com.utico.fooddelivery.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

/*dbName : "Food_Delicious",
        dbName : "UTICO",
    MongoClient.connect(properties.db.azureUri,{ useNewUrlParser: true }, function (err, client) {*/

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

    /*Edit User Profile*/
    @FormUrlEncoded
    @POST("updateUserDetails")
    fun userprofileUpdate(
        @Field("fullName") fullName:String,
        @Field("email")  email:String,
        @Field("userId") userId:String
    ): Call<ProfileEditResponseModel>

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

     /*get The sub category related data*/
    @FormUrlEncoded
    @POST("fetchSubCategoryMenusDetails")
    fun getSubCategoryRelatedDetails(
         @Field("mainCategoryId") mainCategoryId:String,
         @Field("subCategoryId") subCategoryId:String
    ): Call<SubCategoryMenuDetailsModel>

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
        @Field("itemSubCategoryName") itemSubCategoryName:String
        ): Call<SubCategoryMenuDetailsModel>

     /*Fetch the Veg related data*/
     @FormUrlEncoded
     @POST("fetchVegMenusDetails")
      fun getVegMenuDetails(
         @Field("itemMainCategoryName") itemMainCategoryName:String,
         @Field("itemSubCategoryName") itemSubCategoryName:String,
         @Field("itemFoodType") itemFoodType:String
      ) : Call<SubCategoryMenuDetailsModel>

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
    @POST("Placeorder")
    fun placeOrder(
    @Body placeOrderSendDataModel: PlaceOrderSendDataModel
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

}