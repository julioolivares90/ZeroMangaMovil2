package com.zerodev.zeromanga.net.repository

import com.zerodev.zeromanga.net.models.ResponseManga

 interface CharpetersRepository {
    suspend fun GetImagesFromChapters(
        url: String,
        urlRefer: String
    ): ResponseManga<MutableList<String>>
}