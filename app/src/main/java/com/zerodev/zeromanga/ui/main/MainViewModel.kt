package com.zerodev.zeromanga.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.zeromanga.data.remote.models.Response
import com.zerodev.zeromanga.data.remote.models.ResponseManga
import com.zerodev.zeromanga.domain.repository.MangaRepository
import kotlinx.coroutines.launch

class MainViewModel (private val repository: MangaRepository) : ViewModel() {
    //private val repository = MangaRepository()

   private val _mangasSeinen : MutableLiveData<ResponseManga<Response>> = MutableLiveData()

    private val _mangasPopulares : MutableLiveData<ResponseManga<Response>> = MutableLiveData()

    private val _isLoading  : MutableLiveData<Boolean> = MutableLiveData()

    private val _hasError : MutableLiveData<Boolean> = MutableLiveData()

    init {
        setMangas()
    }

    fun IsLoading() : LiveData<Boolean> = _isLoading

    fun hasError() : LiveData<Boolean> =  _hasError
    fun getMangaSeinen() : LiveData<ResponseManga<Response>> = _mangasSeinen

    fun getMangasPopulares() : LiveData<ResponseManga<Response>> = _mangasPopulares

    private  fun setMangas () = viewModelScope.launch {
        _isLoading.postValue(true)
        getAllMangasPopulares()
        getAllMangasSeinen()
        _isLoading.postValue(false)
    }
    fun getAllMangasSeinen()  = viewModelScope.launch {
        when(val response = repository.getAllMangasSeinen()){
            is ResponseManga.Success<Response> -> {
                if (response.data.data.isEmpty()){
                    _hasError.postValue(true)
                }else {
                    _mangasSeinen.postValue(response)
                    _hasError.postValue(false)
                }

            }else -> {
                _hasError.postValue(true)
            }
        }

    }
    fun getAllMangasPopulares() = viewModelScope.launch {
       when (val response = repository.getAllMangasPopulares(1)){
           is  ResponseManga.Success<Response> -> {
               if (response.data.data.isEmpty()){
                   _hasError.postValue(true)
               }else {
                    _mangasPopulares.postValue(response)
                   _hasError.postValue(false)
               }
           }else -> {
           _hasError.postValue(true)
           }
       }

    }
    /*
    private fun setMangasSeinen() {
        viewModelScope.launch(Dispatchers.IO) {
               val result =  repository.getAllMangasSeinen()
            when(result){
                is ResponseManga.Success<Response> -> {
                     _mangasSeinen.postValue(result)
                }else -> {}
            }
        }
    }
    private fun setMangasPopulares() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAllMangasPopulares(1)
            when(result){
                is ResponseManga.Success<Response> -> {
                    _mangasPopulares.postValue(result)
                }else -> {}
            }
        }
    }

     */
}