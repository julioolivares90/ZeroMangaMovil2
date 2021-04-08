package com.zerodev.zeromanga.ui.tmo.lector

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.zeromanga.net.models.ResponseManga
import com.zerodev.zeromanga.net.repository.CharpetersRepository
import kotlinx.coroutines.launch

class LectorViewModel  (private val repository : CharpetersRepository) : ViewModel() {

    private val imagenes = MutableLiveData<MutableList<String>>()

    private val _isloading = MutableLiveData<Boolean>()

    init {
        _isloading.value = true
    }
    fun IsLoading() : LiveData<Boolean> = _isloading

    fun getImagenes(): LiveData<MutableList<String>> = imagenes

    suspend fun getImagenesCap(url : String,mangaUrlRefer : String)  {

         viewModelScope.launch {
             val result : ResponseManga<MutableList<String>>? = repository.GetImagesFromChapters(url = url,mangaUrlRefer)
             when(result){
                 is ResponseManga.Success<MutableList<String>>->{
                     imagenes.postValue(result.data)

                     _isloading.postValue(false)

                 }else -> {
                 _isloading.postValue(false)
             }
             }
         }
    }
}