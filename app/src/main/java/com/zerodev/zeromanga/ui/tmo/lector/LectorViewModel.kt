package com.zerodev.zeromanga.ui.tmo.lector

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.zeromanga.net.models.ResponseManga
import com.zerodev.zeromanga.net.repository.MangaRepository
import com.zerodev.zeromanga.net.scraperTMO.Scraper
import com.zerodev.zeromanga.net.scraperTMO.ScraperTMO
import kotlinx.coroutines.launch

class LectorViewModel : ViewModel() {
    private val scraperTMO = ScraperTMO()
    private  val scraper = Scraper()

    private val imagenes = MutableLiveData<MutableList<String>>()

    private val _isloading = MutableLiveData<Boolean>()

    private val repository = MangaRepository()

    init {
        _isloading.value = true

    }
    fun IsLoading() : LiveData<Boolean> = _isloading

    fun getImagenes(): LiveData<MutableList<String>> = imagenes

    suspend   fun getImagenesCap(url : String)  {
         viewModelScope.launch {
            Log.d("DATA-SRC =>",scraper.getDataSRC(url = url))
             val newUrl = scraper.getDataSRC(url = url)
             newUrl.let {
                 val result : ResponseManga<MutableList<String>>? = repository.getListOfCapitulosfromManga(urlLector = newUrl)
                 when(result){
                     is ResponseManga.Success<MutableList<String>>->{
                         imagenes.postValue(result.data)
                     }else -> {}
                 }
             }
         }
    }
}