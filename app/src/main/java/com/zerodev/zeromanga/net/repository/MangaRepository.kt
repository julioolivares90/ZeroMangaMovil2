package com.zerodev.zeromanga.net.repository

import com.zerodev.zeromanga.net.api.Api
import com.zerodev.zeromanga.net.api.RetrofitSingleton
import com.zerodev.zeromanga.net.models.Response
import com.zerodev.zeromanga.net.models.ResponseManga
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MangaRepository  {

    private val retrofit = RetrofitSingleton.buildService(Api::class.java)

    suspend fun getAllMangasSeinen() : ResponseManga<Response>{
      return withContext(Dispatchers.IO){
          ResponseManga.Success(retrofit.getAllMangasSeinen())
      }
    }

    suspend fun getAllMangasPopulares(pageNumber : Int) : ResponseManga<Response> {
        return withContext(Dispatchers.IO){
            ResponseManga.Success(retrofit.getAllMangasPopulares(pageNumber))
        }
    }
   // suspend fun getAllMangasSeinen() = getResult { retrofit.getAllMangasSeinen()}

    //suspend fun getAllMangasPopulares(pageNumber : Int) = getResult { retrofit.getAllMangasPopulares(pageNumber) }
}