package com.zerodev.zeromanga.domain.repository

import androidx.lifecycle.LiveData
import com.zerodev.zeromanga.data.local.db.models.MangaFav

interface MangaFavRepository {
    suspend fun addMangaFav(mangaFav: MangaFav) : Long

    suspend fun updateMangaFav(mangaFav: MangaFav)

    suspend fun deleteMangaFav(mangaFav: MangaFav)

    fun getAllMangas() : LiveData<List<MangaFav>>

    fun searchManga(query:String) : LiveData<List<MangaFav>>

    fun findMangaByTitle(title: String) : LiveData<List<MangaFav>>
}