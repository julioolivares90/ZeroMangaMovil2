package com.zerodev.zeromanga.ui.tmo.lector

import android.os.Debug
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.zeromanga.data.remote.models.ResponseManga
import com.zerodev.zeromanga.domain.repository.CharpetersRepository
import kotlinx.coroutines.launch

class LectorViewModel  (private val repository : CharpetersRepository) : ViewModel() {

    private val imagenes = MutableLiveData<MutableList<String>>()

    private val _isloading = MutableLiveData<Boolean>()

    private val _hasError = MutableLiveData<Boolean>()

    init {
        _isloading.value = true
        _hasError.value = false
    }
    fun IsLoading() : LiveData<Boolean> = _isloading

    fun getImagenes(): LiveData<MutableList<String>> = imagenes

    fun HasError() : LiveData<Boolean> = _hasError

    suspend fun getImagenesCap(url : String,mangaUrlRefer : String)
    {

         viewModelScope.launch {
             when(val result : ResponseManga<MutableList<String>> = repository.GetImagesFromChapters(url = url,urlRefer = mangaUrlRefer)){

                 is ResponseManga.Success<MutableList<String>>->{
                     if (imagenes.value.isNullOrEmpty()){
                         _hasError.postValue(true)
                         _isloading.postValue(false)
                     } else {
                         imagenes.postValue(result.data)

                         imagenes.value?.forEach { image->
                             Log.println(Log.DEBUG,image,"imagen")
                         }
                         _isloading.postValue(false)
                         _hasError.postValue(false)
                         }
                 }else -> {
                 _isloading.postValue(false)
                 _hasError.postValue(true)
             }
             }
         }
    }
}