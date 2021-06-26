package com.zerodev.zeromanga.ui.busqueda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.zeromanga.data.remote.models.Response
import com.zerodev.zeromanga.data.remote.models.ResponseManga
import com.zerodev.zeromanga.domain.repository.MangaRepository
import kotlinx.coroutines.launch

class BusquedaViewModel  ( private val repository : MangaRepository) : ViewModel() {

    private val _mangasBusqueda : MutableLiveData<ResponseManga<Response>> = MutableLiveData()

    private val _isLoading : MutableLiveData<Boolean> = MutableLiveData()

    private val _hasError : MutableLiveData<Boolean> = MutableLiveData()

    init {
        //setIsLoading(true)
        //setMangasBusqueda()
        //setIsLoading(false)
    }

    fun setMangasBusqueda() =  viewModelScope.launch {
        val response = repository.getMangasLibrary()

        when(response){
            is ResponseManga.Success ->{
                _mangasBusqueda.postValue(response)
                _hasError.postValue(false)
                setIsLoading(false)
            }
            else -> {
                _hasError.postValue(true)
                _isLoading.postValue(false)
            }
        }

    }

    fun hasError() : LiveData<Boolean> = _hasError

    fun setIsLoading(value : Boolean) {
        _isLoading.postValue(value)
    }
    fun getMangasBusqueda() : LiveData<ResponseManga<Response>> = _mangasBusqueda

    fun IsLoading() : LiveData<Boolean> = _isLoading

    fun findMangas(title : String,orderField : String,orderItem : String,orderDir : String) = viewModelScope.launch {

        _isLoading.value = true
        _mangasBusqueda.postValue(null)

       val response =  repository.getMangasLibrary(title = title,orderItem = orderItem,orderDir = orderDir,filter_by = orderField)

        _mangasBusqueda.postValue(response)

        _isLoading.value = false
    }
}