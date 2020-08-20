package com.zerodev.zeromanga.ui.tmo.descripcion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.zeromanga.net.models.MangaResponse
import com.zerodev.zeromanga.net.models.ResponseManga
import com.zerodev.zeromanga.net.repository.MangaRepository
import kotlinx.coroutines.launch

class DescripcionViewModel : ViewModel() {
    private val repository = MangaRepository()

    private val  _infoManga = MutableLiveData<MangaResponse>()

    private val _isLoading  = MutableLiveData<Boolean>()

    init {
        _isLoading.value = true
    }
    fun IsLoading() : LiveData<Boolean> = _isLoading
    fun getInfoManga() : LiveData<MangaResponse> = _infoManga

     fun setInfoManga(mangaUrl : String) = viewModelScope.launch {
         _isLoading.value = true
       val response =  repository.getInfoManga(urlVisitar = mangaUrl)
         when(response){
             is ResponseManga.Success<MangaResponse> ->_infoManga.postValue(response.data)
             else -> {}
         }
         _isLoading.value = false
    }
}