package com.zerodev.zeromanga.data.remote.models

import java.lang.Exception

sealed class ResponseManga<out R> {
     object Loading: ResponseManga<Nothing>()
     data class Success<out T>(val data : T) : ResponseManga<T>()
     data class Error(val exception: Exception) : ResponseManga<Nothing>()

}