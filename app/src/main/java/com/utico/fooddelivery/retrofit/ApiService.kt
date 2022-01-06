package com.utico.fooddelivery.retrofit

import com.utico.fooddelivery.model.DeleteCartItemResponse
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

    /*get MainCategory list*/
    @GET("manager/fetchMainCategory")
    fun fetchMainCategory():Call<MainCategoryResponseModel>

    @FormUrlEncoded
    @POST("user/userRegistration")
    fun userRegistration(
        @Field("fullName") fullName: String,
        @Field("mobileNo") mobileNumber: String,
        @Field("countryCode") countryCode:String,
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
    @POST("user/verifyOtp")
       fun otpVerification(
        @Field("countryCode") countryCode: String,
        @Field("mobileNo") mobileNumber: String,
        @Field("mobileOtp") mobileOtp:String
       ): Call<ResponseBody>

    /*Login ApiService Call*/
   // @Headers("api_key:JPcopEq16fyQGjnzY3QXVDnGDZrgQAs1")
    @FormUrlEncoded
    @POST("user/userLogin")
    fun userLogin(
        @Field("countryCode") countryCode:String,
        @Field("mobileNo") mobileNumber: String
    ) : Call<ResponseBody>

    /*Getting the Food Sub Category Data Method*/
    @FormUrlEncoded
    @POST("manager/fetchSubCategory")
    fun getSubCategoryList(
        @Field("mainCategoryId") mainCategoryId:String
    ): Call<SubCategoryResponseModel>

     /*get The sub category related data*/
    @FormUrlEncoded
    @POST("user/fetchUserMenuOld")
    fun getSubCategoryRelatedDetails(
         @Field("mainCategoryId") mainCategoryId:String,
         @Field("subCategoryId") subCategoryId:String
    ): Call<MenuDetailsResponseDataModel>


    /*get The sub category related data*/
    @FormUrlEncoded
    @POST("user/fetchVegMenu")
    fun getVegMenuDetails(
        @Field("mainCategoryId") mainCategoryId:String,
        @Field("subCategoryId") subCategoryId:String,
    ): Call<MenuDetailsResponseDataModel>

    /*Post AddOns Data*/
    @FormUrlEncoded
    @POST("user/fetchAddOnDetails")
    fun getAddOns(
        @Field("itemId") itemId:String
    ): Call<AddOnsDataModel>



    /*Post addToCart Items data*/
       @FormUrlEncoded
       @POST("user/addToCart")
       fun addToCart(
         @Field("itemMainCategoryName") itemMainCategoryName:String,
         @Field("itemSubCategoryName") itemSubCategoryName:String,
         @Field("itemFoodType") itemFoodType:Boolean,
         @Field("itemName") itemName:String,
         @Field("itemId") itemId:String,
         @Field("itemBaseQuantity") itemQuantity:String,
         @Field("itemPrice") itemPrice:String,
         @Field("itemImageUrl") itemImageUrl:String,
         @Field("itemDescription") itemDescription:String,
         @Field("userId") userId:String,
         @Field("itemBasePrice") itemBasePrice:String
    ): Call<CartIncrementOrDecrementResponse>

    /*Fetch addToCart details*/
    @FormUrlEncoded
    @POST("user/viewCart")
    fun getAddToCartDetails(
        @Field("userId") userId:String
    ): Call<ViewCartResponseModel>

    /*Increment Or Decrement Item*/
    @FormUrlEncoded
    @POST("user/editCart")
    fun editCart(
        @Field("userId") userId:String,
        @Field("cartId") cartId:String,
        @Field("itemBaseQuantity") itemBaseQuantity:String
    ): Call<CartIncrementOrDecrementResponse>


    /*Delete Cart Item*/
    @FormUrlEncoded
    @POST("/user/deleteCart")
      fun deleteCartItem(
        @Field("userId") userId:String,
        @Field("cartId") cartId: String
      ): Call<DeleteCartItemResponse>

    /*Place Order*/
    @POST("user/placeOrder")
    fun placeOrder(
    @Body placeOrderSendDataModel: PlaceOrderSendDataModel
    ): Call<OrderPlacedResponse>


    /*Get the subscription Types*/
    @GET("manager/fetchSubscriptionTitle")
    fun getSubscriptionTypes():Call<SubscriptionTitleDataResponseModel>

    /*Get the fetch upcoming Meals*/
    @FormUrlEncoded
    @POST("user/fetchUpComingMeals")
    fun subscriptionUpcomingMeals(
        @Field("subscriptionId") subscriptionId:String
    ):Call<SubscriptionUpcomingMealResponseModel>

    /*Get the fetch upcoming Meals*/
    @FormUrlEncoded
    @POST("user/fetchSubcriptionPlans")
    fun subscriptionPlans(
        @Field("subscriptionId") subscriptionId:String
    ):Call<SubscriptionPlansResponseModel>


    /*Place the Subscription Plans*/
    @FormUrlEncoded
    @POST("user/placeOrder")
    fun placeSubscription(
        @Field("userId") userId:String,
        @Field("sectorId") sectorId:String,
        @Field("villa") villa:String,
        @Field("landMark") landMark:String,
        @Field("totalAmount") totalAmount:String,
        @Field("categoryType") categoryType:String,
        @Field("subscriptionPlan") plan:String,
        @Field("startDate") startDate:String,
        @Field("subscriptionTitle") subscriptionTitle:String
    ):Call<OrderPlacedResponse>

    /*Place Order History*/
    @FormUrlEncoded
    @POST("user/userOrderHistory")
    fun orderHistory(
     @Field("userId") userId:String
    ): Call<PlacedOrderHistoryResponse>

   /*get the Order Cancel Reason*/
  @GET("user/cancelReason")
    fun orderCancelReason():Call<OrderCancelReasonResponseModel>

    /*Placed Order Cancel*/
    @FormUrlEncoded
    @POST("user/cancelOrder")
    fun placedOderCancel(
     @Field("userId") userId:String,
     @Field("orderId") orderId:String,
     @Field("reason") reason:String,
     ):Call<OrderCancelResponse>

}