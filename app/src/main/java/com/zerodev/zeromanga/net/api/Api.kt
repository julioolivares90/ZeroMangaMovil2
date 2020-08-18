package com.zerodev.zeromanga.net.api

import com.zerodev.zeromanga.net.models.MangaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("manga/populares")
    suspend fun getAllMangasPopulares(@Query("pageNumber") pgeNumber : Int) : com.zerodev.zeromanga.net.models.Response

    @GET("manga/populares-seinen")
    suspend fun getAllMangasSeinen() : com.zerodev.zeromanga.net.models.Response

    @GET("manga/info")
    suspend fun getInfoManga(@Query("mangaUrl") mangaUrl : String) : Response<MangaResponse>

}