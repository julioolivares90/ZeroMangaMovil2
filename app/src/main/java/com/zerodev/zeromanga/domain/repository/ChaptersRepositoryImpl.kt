package com.zerodev.zeromanga.domain.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.zerodev.zeromanga.data.remote.models.ResponseManga
import com.zerodev.zeromanga.domain.scraperTMO.ScraperChapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ChaptersRepositoryImpl : CharpetersRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun GetImagesFromChapters(url : String, urlRefer : String) : ResponseManga<MutableList<String>> {
        val scraper = ScraperChapter()

        val imagenes = withContext(Dispatchers.IO){
            scraper.GetImagesFromChapters(urlRefer,url)
        }

        if (imagenes.size > 0)
            return ResponseManga.Success(imagenes)
        return ResponseManga.Error(Exception("Error No se encontraron capitulos"))
    }
}