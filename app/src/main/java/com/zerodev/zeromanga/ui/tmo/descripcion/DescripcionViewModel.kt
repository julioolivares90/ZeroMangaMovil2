package com.zerodev.zeromanga.ui.tmo.descripcion

import android.app.Application
import android.os.Debug
import android.util.Log
import androidx.lifecycle.*
import com.zerodev.zeromanga.data.local.db.models.MangaFav
import com.zerodev.zeromanga.data.remote.models.MangaResponse
import com.zerodev.zeromanga.data.remote.models.ResponseManga
import com.zerodev.zeromanga.domain.repository.MangaFavRepository
import com.zerodev.zeromanga.domain.repository.MangaRepository
import kotlinx.coroutines.launch

class DescripcionViewModel  (application: Application,
                             private val mangaFavRepository: MangaFavRepository,
                             private val repository: MangaRepository) : AndroidViewModel(application) {

    private val  _infoManga = MutableLiveData<MangaResponse>()

    private val _isLoading  = MutableLiveData<Boolean>()

    private val _mangaExist = MutableLiveData<Boolean>()

    init {
        _isLoading.value = true
        _mangaExist.value = false
    }

    fun IsLoading() : LiveData<Boolean> = _isLoading

    fun getInfoManga() : LiveData<MangaResponse> = _infoManga

    fun MangaExist() : LiveData<Boolean> = _mangaExist

     fun setInfoManga(mangaUrl : String) = viewModelScope.launch {

         _isLoading.value = true
         val response =  repository.getInfoManga(urlVisitar = mangaUrl)
         when(response){
             is ResponseManga.Success<MangaResponse> ->_infoManga.postValue(response.data)
             else -> {
             }
         }
         _isLoading.value = false
    }

    fun mangaExiste(title : String) : Boolean {
        val manga = mangaFavRepository.findMangaByTitle(title)
        if (manga.value != null)
            return true
        return false
    }
    fun addMangaToFavorites(mangaFav: MangaFav) = viewModelScope.launch{
        try {

                val rowsAfected =   mangaFavRepository.addMangaFav(mangaFav)
                if (rowsAfected > 0){
                    Log.d("ROWS AFECTED =>",rowsAfected.toString())
                }


        }catch (ex : Exception){
            println(ex.message)
        }
    }
}