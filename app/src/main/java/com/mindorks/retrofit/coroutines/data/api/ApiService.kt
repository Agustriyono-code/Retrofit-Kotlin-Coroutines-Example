package com.mindorks.retrofit.coroutines.data.api

import com.mindorks.retrofit.coroutines.data.model.User
import retrofit2.http.GET

interface ApiService {

    //mengambil data user terus dimasukan dalam list

    @GET("users")
    suspend fun getUsers(): List<User>

}