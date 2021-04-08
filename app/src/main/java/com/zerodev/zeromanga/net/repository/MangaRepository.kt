package com.zerodev.zeromanga.net.repository

import com.zerodev.zeromanga.net.models.MangaResponse
import com.zerodev.zeromanga.net.models.Response
import com.zerodev.zeromanga.net.models.ResponseManga

interface MangaRepository {
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
}