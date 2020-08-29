package com.zerodev.zeromanga.ui.busqueda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.zeromanga.net.models.*
import com.zerodev.zeromanga.net.repository.MangaRepository
import kotlinx.coroutines.launch

class BusquedaViewModel : ViewModel() {

    private val repository = MangaRepository()

    private val _mangasBusqueda : MutableLiveData<ResponseManga<Response>> = MutableLiveData()

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData()

    init {
        _isLoading.value = true
        setMangasBusqueda()
        _isLoading.value = false
    }

    fun setMangasBusqueda() =  viewModelScope.launch {
            val response = repository.getMangasLibrary()
            _mangasBusqueda.postValue(response)
        }


    fun getMangasBusqueda() : LiveData<ResponseManga<Response>> = _mangasBusqueda

    fun IsLoading() : LiveData<Boolean> = _isLoading

    fun findMangas(title : String,orderField : String,orderItem : String,orderDir : String) = viewModelScope.launch{
        _mangasBusqueda.postValue(null)
       val response =  repository.getMangasLibrary(title = title,orderItem = orderItem,orderDir = orderDir,filter_by = orderField)
        _mangasBusqueda.postValue(response)
    }
}