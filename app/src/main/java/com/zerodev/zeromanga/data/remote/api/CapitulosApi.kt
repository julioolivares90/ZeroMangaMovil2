package com.zerodev.zeromanga.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query

interface CapitulosApi {
    @GET("Manga-paginas")
    suspend fun getPaginasFromChapter(@Query("urlRefer") urlRefer: String
                                      ,@Query("urlCapitulo") urlChapter: String)
}