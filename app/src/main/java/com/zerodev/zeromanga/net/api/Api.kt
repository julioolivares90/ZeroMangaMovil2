package com.zerodev.zeromanga.net.api

import com.zerodev.zeromanga.net.models.MangaResponse
import com.zerodev.zeromanga.net.models.ResponseLista
import com.zerodev.zeromanga.net.models.ResultListPaginasManga
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("manga/populares")
    suspend fun getAllMangasPopulares(@Query("pageNumber") pgeNumber : Int) : com.zerodev.zeromanga.net.models.Response

    @GET("manga/populares-seinen")
    suspend fun getAllMangasSeinen() : com.zerodev.zeromanga.net.models.Response

    @GET("manga/info")
    suspend fun getInfoManga(@Query("mangaUrl") mangaUrl : String) : MangaResponse

    @GET("manga/library")
    suspend fun getMangasLibrary(
        @Query("title") title: String?,
        @Query("_page") page : String?,
        @Query("orderItem") orderItem : String?,
        @Query("orderDir") orderDir: String?,
        @Query("filter_by") filter_by : String?,
        @Query("Type") Type : String?,
        @Query("demography") demography : String?,
        @Query("status") status : String?,
        @Query("translation_status") translation_status : String?,
        @Query("webcomic") webcomic : String?,
        @Query("yonkoma") yonkoma : String?,
        @Query("amateur") amateur : String?,
        @Query("erotic") erotic : String?) : com.zerodev.zeromanga.net.models.Response


    @GET("manga/listas")
    suspend fun getListasMangas() : ResponseLista

    @GET("get-manga")
    suspend fun getPaginasOfManga(@Query("urlPage") lectorTMO : String) : ResultListPaginasManga

}