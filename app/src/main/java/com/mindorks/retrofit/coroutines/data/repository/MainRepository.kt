package com.mindorks.retrofit.coroutines.data.repository

import com.mindorks.retrofit.coroutines.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    //mengambil data dari apiservis
    suspend fun getUsers() = apiHelper.getUsers()
}