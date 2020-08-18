package com.zerodev.zeromanga.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.zeromanga.net.models.Response
import com.zerodev.zeromanga.net.models.ResponseManga
import com.zerodev.zeromanga.net.repository.MangaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository = MangaRepository()

   private val _mangasSeinen : MutableLiveData<ResponseManga<Response>> = MutableLiveData()

    private val _mangasPopulares : MutableLiveData<ResponseManga<Response>> = MutableLiveData()

    init {
        getAllMangasSeinen()
        getAllMangasPopulares()
    }

    fun getMangaSeinen() : LiveData<ResponseManga<Response>> = _mangasSeinen

    fun getMangasPopulares() : LiveData<ResponseManga<Response>> = _mangasPopulares

    fun getAllMangasSeinen()  = viewModelScope.launch {
        val response = repository.getAllMangasSeinen()
        _mangasSeinen.postValue(response)
    }
    fun getAllMangasPopulares() = viewModelScope.launch {
        val response = repository.getAllMangasPopulares(1)
        _mangasPopulares.postValue(response)
    }
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
}