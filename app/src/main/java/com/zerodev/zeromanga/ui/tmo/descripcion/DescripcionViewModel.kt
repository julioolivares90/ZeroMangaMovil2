package com.zerodev.zeromanga.ui.tmo.descripcion

import android.app.Application
import androidx.lifecycle.*
import com.zerodev.zeromanga.data.local.db.models.MangaFav
import com.zerodev.zeromanga.data.remote.models.MangaResponse
import com.zerodev.zeromanga.data.remote.models.ResponseManga
import com.zerodev.zeromanga.domain.repository.MangaFavRepository
import com.zerodev.zeromanga.domain.repository.MangaFavRepositoryImpl
import com.zerodev.zeromanga.domain.repository.MangaRepository
import com.zerodev.zeromanga.domain.repository.MangaRepositoryImpl
import kotlinx.coroutines.launch

class DescripcionViewModel  (application: Application,
                             private val mangaFavRepository: MangaFavRepository,
                             private val repository: MangaRepository
) : AndroidViewModel(application) {

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

    fun addMangaToFavorites(mangaFav: MangaFav) = viewModelScope.launch{
        mangaFavRepository.addMangaFav(mangaFav)
    }
}