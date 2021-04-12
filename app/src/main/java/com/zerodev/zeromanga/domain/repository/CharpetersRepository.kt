package com.zerodev.zeromanga.domain.repository

import com.zerodev.zeromanga.data.remote.models.ResponseManga

 interface CharpetersRepository {
    suspend fun GetImagesFromChapters(
        url: String,
        urlRefer: String
    ): ResponseManga<MutableList<String>>
}