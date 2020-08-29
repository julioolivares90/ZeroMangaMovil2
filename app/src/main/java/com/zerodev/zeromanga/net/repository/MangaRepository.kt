package com.zerodev.zeromanga.net.repository

import com.zerodev.zeromanga.net.api.Api
import com.zerodev.zeromanga.net.api.RetrofitSingleton
import com.zerodev.zeromanga.net.models.MangaResponse
import com.zerodev.zeromanga.net.models.Response
import com.zerodev.zeromanga.net.models.ResponseLista
import com.zerodev.zeromanga.net.models.ResponseManga
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Query
import java.lang.Exception

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

    suspend fun getInfoManga(urlVisitar : String) : ResponseManga<MangaResponse> {
        return  withContext(Dispatchers.IO) {
            val result = retrofit.getInfoManga(urlVisitar)
            if (result.statusCode == 200)
                ResponseManga.Success(result)
            else
                ResponseManga.Error(Exception("Ocurrio un error ${result.statusCode}"))
        }
    }

    suspend fun getMangasLibrary( title: String? = "",
                                  page : String? = "",
                                  orderItem : String? = "",
                                  orderDir: String? = "",
                                  filter_by : String? = "",
                                 Type : String?= "",
                                 demography : String?= "",
                                 status : String?="",
                                 translation_status : String?="",
                                 webcomic : String?= "",
                                 yonkoma : String?="",
                                  amateur : String?="",
                                 erotic : String?=""
    ) : ResponseManga<Response>  {
        return withContext(Dispatchers.IO) {
            val response = retrofit.getMangasLibrary(title,
                page,
                orderDir = orderItem,
                orderItem = orderDir,
                filter_by = filter_by,
                Type = Type,
                demography = demography,
                status = status,
                translation_status = translation_status,
                webcomic = webcomic,
                yonkoma = yonkoma,
                amateur = amateur,
                erotic = erotic)
            if (response.statusCode == 200){
                ResponseManga.Success(response)
            }
            else {
                ResponseManga.Error(exception = Exception("error"))
            }
        }
    }

    suspend fun getListasMangas() : ResponseManga<ResponseLista> {
        return withContext(Dispatchers.IO){
            val response = retrofit.getListasMangas()
            if (response.statusCode == 200){
                ResponseManga.Success(response)
            }else{
                ResponseManga.Error(Exception("Ocurrio un error ${response.statusCode}"))
            }
        }
    }
   // suspend fun getAllMangasSeinen() = getResult { retrofit.getAllMangasSeinen()}

    //suspend fun getAllMangasPopulares(pageNumber : Int) = getResult { retrofit.getAllMangasPopulares(pageNumber) }
}