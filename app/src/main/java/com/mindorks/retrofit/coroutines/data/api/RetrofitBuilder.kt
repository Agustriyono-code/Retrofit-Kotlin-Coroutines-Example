package com.mindorks.retrofit.coroutines.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    //mengambil data melalui internet
    //dengan menggunkan link _url
    private const val BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"

    //mengambil data retrofit apiservice
    //dengan menggunkan link url
    //dan di format dgn format json
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}