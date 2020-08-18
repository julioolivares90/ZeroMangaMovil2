package com.zerodev.zeromanga.net.models

import java.lang.Exception

sealed class ResponseManga<out R> {
     data class Success<out T>(val data : T) : ResponseManga<T>()
     data class Error(val exception: Exception) : ResponseManga<Nothing>()

}