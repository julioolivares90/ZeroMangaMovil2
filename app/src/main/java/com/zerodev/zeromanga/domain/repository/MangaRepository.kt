package com.zerodev.zeromanga.domain.repository

import com.zerodev.zeromanga.data.remote.models.*

interface MangaRepository {
    suspend fun getMangaData() : ResponseManga<MangaData>
    suspend fun getAllMangasSeinen() : ResponseManga<Response>

    suspend fun getAllMangasPopulares(pageNumber : Int) : ResponseManga<Response>

    suspend fun getInfoManga(urlVisitar : String) : ResponseManga<MangaResponse>

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
    ) : ResponseManga<Response>


    suspend fun getPaginasFromChapter(urlRefer:String, urlCapitulo : String) : ResponseManga<Chapters>
}