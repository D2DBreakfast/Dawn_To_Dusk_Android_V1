package com.utico.fooddelivery.retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {
        //val baseUrl = "https://reqres.in/api/"
/*
        val baseUrl ="https://d2dbackendmain.azurewebsites.net/"
*/
        val baseUrl="http://10.12.12.67:3000/"
        fun getRetroInstance(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)

           return Retrofit.Builder()
               .baseUrl(baseUrl)
               .client(client.build())
               .addConverterFactory(GsonConverterFactory.create())
               .build()
        }
    }
}