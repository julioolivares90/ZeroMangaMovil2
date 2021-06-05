package com.zerodev.zeromanga.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zerodev.zeromanga.data.remote.models.MangaData
import com.zerodev.zeromanga.data.remote.models.ResponseManga
import com.zerodev.zeromanga.domain.repository.MangaRepository
import com.zerodev.zeromanga.utlities.CheckNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (private val repository: MangaRepository,private val context: Context) : ViewModel() {

    private val _mangaData : MutableLiveData<ResponseManga<MangaData>> = MutableLiveData()

    private val _isLoading  : MutableLiveData<Boolean> = MutableLiveData()

    private val _hasError : MutableLiveData<Boolean> = MutableLiveData()


    init {
        //setMangas()
    }

    fun getMangaData() : LiveData<ResponseManga<MangaData>> = _mangaData



    fun IsLoading() : LiveData<Boolean> = _isLoading

    fun hasError() : LiveData<Boolean> =  _hasError

    private  fun setMangas () = viewModelScope.launch {
        _isLoading.postValue(true)
        setMangaData()
        _isLoading.postValue(false)
    }

    fun setMangaData() = viewModelScope.launch {

        _isLoading.postValue(true)
        _hasError.postValue(false)
            val result = repository.getMangaData()
            when(result){
                is ResponseManga.Success -> {
                    if (result.data.statusCode == 200){
                        _hasError.postValue(false)
                        _mangaData.postValue(result)
                        _isLoading.postValue(false)
                    }
                }
                else -> {
                    _hasError.postValue(true)
                    _isLoading.postValue(false)
                }
            }
    }

}