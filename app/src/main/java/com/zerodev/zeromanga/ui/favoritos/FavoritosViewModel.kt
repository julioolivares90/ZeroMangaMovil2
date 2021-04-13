package com.zerodev.zeromanga.ui.favoritos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.zeromanga.data.local.db.MangaFavDao
import com.zerodev.zeromanga.data.local.db.models.MangaFav
import com.zerodev.zeromanga.domain.repository.MangaFavRepository
import kotlinx.coroutines.launch

class FavoritosViewModel  (private val mangaFavRepository: MangaFavRepository) : ViewModel() {

    private val _mangasFav : MutableLiveData<List<MangaFav>> = MutableLiveData()

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    
    fun getMangasFav() = mangaFavRepository.getAllMangas()
    
    fun findMangaFav(query : String) {
        _isLoading.value = true
        val busqueda = mangaFavRepository.searchManga(query = query)
       _mangasFav.value = busqueda.value
        _isLoading.value = false
    }

    fun deleteMangaFav(mangaFav: MangaFav) = viewModelScope.launch {
        mangaFavRepository.deleteMangaFav(mangaFav)
    }
    
    fun getMangas() : LiveData<List<MangaFav>> = _mangasFav

    fun isLoading() : LiveData<Boolean> = _isLoading
}