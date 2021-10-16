package com.utico.fooddelivery.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utico.fooddelivery.model.AddToCartDetailsResponseModel
import com.utico.fooddelivery.model.OrderPlacedResponse
import com.utico.fooddelivery.retrofit.ApiService
import com.utico.fooddelivery.retrofit.RetroInstance
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel : ViewModel() {
   private var cartResponseData:MutableLiveData<AddToCartDetailsResponseModel>
   private var cartPlaceResponse:MutableLiveData<OrderPlacedResponse>

    init {
        cartResponseData = MutableLiveData()
        cartPlaceResponse = MutableLiveData()
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

    fun apiCallPlaceOrder(itemMainCategoryName:String,itemSubCategoryName:String,itemFoodType:String,itemName:String,itemId:String,itemQuantity:String,itemPrice:String,userId:String){
        val retroInstance = RetroInstance.getRetroInstance().create(ApiService::class.java)
        val call = retroInstance.placeOrder(itemMainCategoryName,itemSubCategoryName,itemFoodType,itemName,itemId,itemQuantity,itemPrice,userId)
            call.enqueue(object :Callback<OrderPlacedResponse>{
                override fun onResponse(call: Call<OrderPlacedResponse>, response: Response<OrderPlacedResponse>) {
                    if (response.isSuccessful){
                        cartPlaceResponse.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<OrderPlacedResponse>, t: Throwable) {
                    cartResponseData.postValue(null)

                }

            })
    }

}