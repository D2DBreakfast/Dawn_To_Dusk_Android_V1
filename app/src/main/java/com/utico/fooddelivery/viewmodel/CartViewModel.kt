package com.utico.fooddelivery.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.model.AddToCartDetailsResponseModel
import com.utico.fooddelivery.model.OrderPlacedResponse
import com.utico.fooddelivery.model.PlaceOrderArray
import com.utico.fooddelivery.model.PlaceOrderSendDataModel
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel : ViewModel() {
   private var cartResponseData:MutableLiveData<AddToCartDetailsResponseModel>
   private var cartPlaceResponse:MutableLiveData<OrderPlacedResponse>
   private lateinit var errorResponse:MutableLiveData<String>

    init {
        cartResponseData = MutableLiveData()
        cartPlaceResponse = MutableLiveData()
        errorResponse = MutableLiveData()
    }

    /*This is used to fetch the data from addToCart*/
    fun getCartDetailsObservable():MutableLiveData<AddToCartDetailsResponseModel>{
        return cartResponseData
    }

    fun apiCall(userId:String){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.getAddToCartDetails(userId)
            call.enqueue(object :Callback<AddToCartDetailsResponseModel>{
                override fun onResponse(call: Call<AddToCartDetailsResponseModel>,
                    response: Response<AddToCartDetailsResponseModel>) {
                    if (response.isSuccessful){
                        cartResponseData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<AddToCartDetailsResponseModel>, t: Throwable) {
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

}