package com.mindorks.retrofit.coroutines.data.api

class ApiHelper(private val apiService: ApiService) {

    //mengambil data user dari apiservice
    suspend fun getUsers() = apiService.getUsers()
}