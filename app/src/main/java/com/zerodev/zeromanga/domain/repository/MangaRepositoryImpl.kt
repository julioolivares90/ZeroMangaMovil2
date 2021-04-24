package com.zerodev.zeromanga.domain.repository

import android.util.Log
import com.zerodev.zeromanga.data.remote.api.Api
import com.zerodev.zeromanga.data.remote.api.RetrofitSingleton
import com.zerodev.zeromanga.data.remote.models.MangaResponse
import com.zerodev.zeromanga.data.remote.models.Response
import com.zerodev.zeromanga.data.remote.models.ResponseLista
import com.zerodev.zeromanga.data.remote.models.ResponseManga
import com.zerodev.zeromanga.data.remote.models.*
import com.zerodev.zeromanga.utlities.AdapterString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.http.Query
import kotlin.Exception

class MangaRepositoryImpl  (private val retrofit : Api) : MangaRepository {

   override suspend fun getAllMangasSeinen() : ResponseManga<Response> {
      return withContext(Dispatchers.IO){
          try{
              ResponseManga.Success(retrofit.getAllMangasSeinen())
          }catch (ex : Exception){
              ResponseManga.Error(ex)
          }
      }
    }

    override suspend fun getAllMangasPopulares(pageNumber : Int) : ResponseManga<Response> {
        return withContext(Dispatchers.IO){
            try {
                ResponseManga.Success(retrofit.getAllMangasPopulares(pageNumber))
            }catch (ex : Exception){
                ResponseManga.Error(ex)
            }
        }
    }

    override suspend fun getInfoManga(urlVisitar : String) : ResponseManga<MangaResponse> {
        return  withContext(Dispatchers.IO) {
            try {
                val result = retrofit.getInfoManga(urlVisitar)
                if (result.statusCode == 200)
                    ResponseManga.Success(result)
                else
                    ResponseManga.Error(Exception("Ocurrio un error ${result.statusCode}"))
            }catch (ex: Exception){
                ResponseManga.Error(ex)
            }

        }
    }

   override suspend fun getMangasLibrary(title: String?,
                                         page : String?,
                                         orderItem : String?,
                                         orderDir: String?,
                                         filter_by : String?,
                                         Type : String?,
                                         demography : String?,
                                         status : String?,
                                         translation_status : String?,
                                         webcomic : String?,
                                         yonkoma : String?,
                                         amateur : String?,
                                         erotic : String?
   ) : ResponseManga<Response> {
        return withContext(Dispatchers.IO) {
            try {
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
            }catch (ex: Exception){
                ResponseManga.Error(ex)
            }

        }
    }

    suspend fun getListasMangas() : ResponseManga<ResponseLista> {
        return withContext(Dispatchers.IO){
            try {
                val response = retrofit.getListasMangas()
                if (response.statusCode == 200){
                    ResponseManga.Success(response)
                }else{
                    ResponseManga.Error(Exception("Ocurrio un error ${response.statusCode}"))
                }
            }catch (ex: Exception){
                ResponseManga.Error(ex)
            }

        }
    }

    suspend fun getListOfCapitulosfromManga(urlLector : String) : ResponseManga<MutableList<String>> {
        return withContext(Dispatchers.IO){
            try {
                val url = AdapterString(urlLector)
                val response = retrofit.getPaginasOfManga(lectorTMO = url)
                Log.d("RESPONSE",response.toString())
                if (response.statusCode == 200){
                    ResponseManga.Success(response.data)
                }else {
                    ResponseManga.Error(Exception("Ocurrio un error ${response.statusCode}"))
                }
            }catch (ex : Exception){
                ResponseManga.Error(exception = ex)
            }

        }
    }
   // suspend fun getAllMangasSeinen() = getResult { retrofit.getAllMangasSeinen()}

    //suspend fun getAllMangasPopulares(pageNumber : Int) = getResult { retrofit.getAllMangasPopulares(pageNumber) }
}
