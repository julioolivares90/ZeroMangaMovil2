package com.zerodev.zeromanga.ui.tmo.descripcion

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.zeromanga.net.models.MangaResponse
import com.zerodev.zeromanga.net.models.ResponseManga
import com.zerodev.zeromanga.net.repository.MangaFavRepository
import com.zerodev.zeromanga.net.repository.MangaFavRepositoryImpl
import com.zerodev.zeromanga.net.repository.MangaRepository
import com.zerodev.zeromanga.net.repository.MangaRepositoryImpl
import kotlinx.coroutines.launch

class DescripcionViewModel  (val application: Application,
                             private val mangaFavRepository: MangaFavRepository,
                             private val repository: MangaRepository
) : ViewModel() {

    //private val repository = MangaRepository()

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