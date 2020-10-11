package com.zerodev.zeromanga.ui.tmo.lector

import android.util.Log
import android.widget.Toast
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

    private  val TMO_SESSION = MutableLiveData<String>()
    private  val _cfduid = MutableLiveData<String>()
    private  val _ga = MutableLiveData<String>()
    private  val _cf_bm = MutableLiveData<String>()
    private  val _xsrf_token = MutableLiveData<String>()

     fun setTmoSession(tmoSession : String,cfduid : String,ga : String,cf_bm : String,xsrf_token : String){
        TMO_SESSION.value = tmoSession
        _cfduid.value = cfduid
        _ga.value = ga
        _cf_bm.value = cf_bm
        _xsrf_token.value = xsrf_token
    }

    init {
        _isloading.value = true
    }
    fun IsLoading() : LiveData<Boolean> = _isloading

    fun getImagenes(): LiveData<MutableList<String>> = imagenes

    suspend fun getImagenesCap(url : String)  {

         viewModelScope.launch {

             val cookies = mapOf(
                 "tu_manga_online_session" to TMO_SESSION.value,
                 "_cfduid" to _cfduid.value,
                 "_ga" to _ga.value,
                 "__cf_bm" to _cf_bm.value,
                 "XSRF_TOKEN" to _xsrf_token.value
             )
             if (url.contains("lectortmo.com")){
                 val dataSRC = scraper.getDataSRC(url = url,cookies = cookies)
                 Log.d("DATA-SRC =>",dataSRC)

                 val newUrl = dataSRC
                 newUrl?.let {
                     val result : ResponseManga<MutableList<String>>? = repository.getListOfCapitulosfromManga(urlLector = newUrl)
                     when(result){
                         is ResponseManga.Success<MutableList<String>>->{
                             imagenes.postValue(result.data)
                             _isloading.postValue(false)
                         }else -> {
                         _isloading.postValue(false)
                     }
                     }
                 }
             }else {
                 cookies.let {c->
                     val dataSRC = scraper.getDataSRC(url = url,cookies =c)
                     val newUrl = dataSRC
                     newUrl?.let {
                         val result : ResponseManga<MutableList<String>> = repository.getListOfCapitulosfromManga(urlLector = it)
                         when(result){
                             is ResponseManga.Success<MutableList<String>>-> {
                                 imagenes.postValue(result.data)
                                 _isloading.postValue(false)
                             }else -> {
                             _isloading.postValue(false)
                         }
                         }
                     }
                 }
             }
         }
    }
}