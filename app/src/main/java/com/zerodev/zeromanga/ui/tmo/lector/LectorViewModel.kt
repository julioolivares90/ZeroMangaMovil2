package com.zerodev.zeromanga.ui.tmo.lector

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.zeromanga.net.scraperTMO.ScraperTMO
import kotlinx.coroutines.launch

class LectorViewModel : ViewModel() {
    private val scraperTMO = ScraperTMO()
    private val imagenes = MutableLiveData<ArrayList<String>>()

    private val _isloading = MutableLiveData<Boolean>()

    init {
        _isloading.value = true
    }
    fun IsLoading() : LiveData<Boolean> = _isloading

    fun getImagenes(): LiveData<ArrayList<String>> = imagenes
    suspend   fun getImagenesCap(url : String)  {
         viewModelScope.launch {
           imagenes.postValue(scraperTMO.getImagesNumberFromCapitulo(url))
         }
    }
}