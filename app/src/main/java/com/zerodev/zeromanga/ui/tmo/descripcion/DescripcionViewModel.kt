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
import timber.log.Timber

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
             is ResponseManga.Success<MangaResponse> ->_infoManga.postValue(response.data!!)
             else -> {
             }
         }
         _isLoading.value = false
    }

    //busca un manga por titulo
    fun mangaExiste(title : String) : Boolean {

        val search = mangaFavRepository.findMangaByTitle(title.replace("\n",""))

        val result = search.value?.single { (_, value) ->
            value.contains(title)
        }

        println(" Manga fav => ${result?.title}")
        if (search.value != null){
            if (result != null){
                return true
            }
            return  false
        }
         return false
    }
    //add manga to list favorite
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