package com.zerodev.zeromanga.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import androidx.lifecycle.viewModelScope
import com.zerodev.zeromanga.data.remote.models.MangaData
import com.zerodev.zeromanga.data.remote.models.ResponseManga
import com.zerodev.zeromanga.domain.repository.MangaRepository
import com.zerodev.zeromanga.utlities.CheckNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel (private val repository: MangaRepository,private val context: Context) : ViewModel() {

    private val _mangaData : MutableLiveData<ResponseManga<MangaData>> = MutableLiveData()

    private val _isLoading  : MutableLiveData<Boolean> = MutableLiveData()

    private val _hasError : MutableLiveData<Boolean> = MutableLiveData()

    init {
        //setMangas()
    }

    fun IsLoading() : LiveData<Boolean> = _isLoading

    fun hasError() : LiveData<Boolean> =  _hasError

    fun getMangaData() = liveData<ResponseManga<MangaData>>(viewModelScope.coroutineContext + Dispatchers.IO){
        emit(ResponseManga.Loading)
        try {
            repository.getMangaData().collect {
                emit(it)
            }
        }
        catch (ex : Exception){
            emit(ResponseManga.Error(ex))
        }
    }
    val mangaData : Flow<ResponseManga<MangaData>> = flow {
        liveData {
            emit(ResponseManga.Loading)
            repository.getMangaData().catch{ emit(ResponseManga.Error(Exception("Ocurrio un error"))) }
                .collect {
                emit(it)
            }
        }
    }
}