package com.zerodev.zeromanga.net.repository

import com.zerodev.zeromanga.net.models.ResponseManga
import com.zerodev.zeromanga.net.scraperTMO.ScraperChapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChaptersRepositoryImpl : CharpetersRepository {
    override suspend fun GetImagesFromChapters(url : String, urlRefer : String) : ResponseManga<MutableList<String>> {
        val scraper = ScraperChapter()

        val imagenes = withContext(Dispatchers.IO){ scraper.GetImagesFromChapters(urlRefer,url)}

        if (imagenes.size > 0)
            return ResponseManga.Success(imagenes)
        return ResponseManga.Error(Exception("Error No se encontraron capitulos"))
    }
}