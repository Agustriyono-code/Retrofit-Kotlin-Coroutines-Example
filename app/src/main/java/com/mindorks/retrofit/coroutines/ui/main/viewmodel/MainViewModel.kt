package com.mindorks.retrofit.coroutines.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mindorks.retrofit.coroutines.data.repository.MainRepository
import com.mindorks.retrofit.coroutines.utils.Resource
import kotlinx.coroutines.Dispatchers

//TODO 4 :view model
//mengakses view model
class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    //mengambil data user dengan livedata
    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        //untuk mengetahui jika program bernilai benar atau eror
        //jika eror maka akan tampil massage error occured
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}