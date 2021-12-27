package com.utico.fooddelivery.viewmodel
import com.utico.fooddelivery.model.DeleteCartItemResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.model.*
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel : ViewModel() {
   private var cartResponseData:MutableLiveData<ViewCartResponseModel>
   private var cartPlaceResponse:MutableLiveData<OrderPlacedResponse>
   private lateinit var errorResponse:MutableLiveData<String>
   private var deleteCartItemResponse:MutableLiveData<DeleteCartItemResponse>
   private var incrementOrDecrementCartItemResponse:MutableLiveData<CartIncrementOrDecrementResponse>

    init {
        cartResponseData = MutableLiveData()
        cartPlaceResponse = MutableLiveData()
        errorResponse = MutableLiveData()
        deleteCartItemResponse = MutableLiveData()
        incrementOrDecrementCartItemResponse = MutableLiveData()

    }

    /*This is used to fetch the data from addToCart*/
    fun getCartDetailsObservable():MutableLiveData<ViewCartResponseModel>{
        return cartResponseData
    }

    fun apiCall(userId:String){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getAddToCartDetails(userId)
            call.enqueue(object :Callback<ViewCartResponseModel>{
                override fun onResponse(call: Call<ViewCartResponseModel>,
                    response: Response<ViewCartResponseModel>) {
                    if (response.isSuccessful){
                        cartResponseData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ViewCartResponseModel>, t: Throwable) {
                    cartResponseData.postValue(null)
                }

            })
    }

    fun placeObservable():MutableLiveData<OrderPlacedResponse>{
        return cartPlaceResponse
    }

    fun apiCallPlaceOrder(placeOrderSendDataModel: PlaceOrderSendDataModel){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.placeOrder(placeOrderSendDataModel!!)
            call.enqueue(object :Callback<OrderPlacedResponse>{
                override fun onResponse(call: Call<OrderPlacedResponse>, response: Response<OrderPlacedResponse>) {
                    if (response.isSuccessful){
                        cartPlaceResponse.postValue(response.body())
                    }else{
                        errorResponse.postValue(response.errorBody().toString())

                    }
                }

                override fun onFailure(call: Call<OrderPlacedResponse>, t: Throwable) {
                    cartResponseData.postValue(null)
                    errorResponse.postValue(t.toString())

                }

            })
    }

    /*Delete Particular Cart Item*/
    fun deleteCardItemObservable():MutableLiveData<DeleteCartItemResponse>{
        return deleteCartItemResponse
    }

    fun apiCallCartItemDelete(userId: String,cardId:String){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.deleteCartItem(userId,cardId)
            call.enqueue(object :Callback<DeleteCartItemResponse>{
                override fun onResponse(call: Call<DeleteCartItemResponse>, response: Response<DeleteCartItemResponse>) {
                    if (response.isSuccessful){
                        deleteCartItemResponse.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DeleteCartItemResponse>, t: Throwable) {
                    deleteCartItemResponse.postValue(null)
                }

            })
    }


    /*Increment Or Decrement Particular Cart Item*/

    fun incrementOrDecrementCartObservable():MutableLiveData<CartIncrementOrDecrementResponse>{
      return incrementOrDecrementCartItemResponse
    }

    fun callApiIcrementDecrementCartItem(userId:String,cartId:String,itemBaseQuantity:String){
       val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
       val call = retroInstance.editCart(userId,cartId,itemBaseQuantity)
           call.enqueue(object :Callback<CartIncrementOrDecrementResponse>{
               override fun onResponse(call: Call<CartIncrementOrDecrementResponse>, response: Response<CartIncrementOrDecrementResponse>) {
                   if (response.isSuccessful){
                       incrementOrDecrementCartItemResponse.postValue(response.body())
                   }
               }

               override fun onFailure(call: Call<CartIncrementOrDecrementResponse>, t: Throwable) {
                   incrementOrDecrementCartItemResponse.postValue(null)

               }

           })
    }

}