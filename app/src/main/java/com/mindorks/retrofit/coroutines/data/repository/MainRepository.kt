package com.mindorks.retrofit.coroutines.data.repository

import com.mindorks.retrofit.coroutines.data.api.ApiHelper
//TODO 7 :Bagian untuk parsing data json hasil response --> Library yang digunakan
class MainRepository(private val apiHelper: ApiHelper) {

    //mengambil data dari apiservis
    suspend fun getUsers() = apiHelper.getUsers()
}