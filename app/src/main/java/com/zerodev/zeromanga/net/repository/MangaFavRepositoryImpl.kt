package com.zerodev.zeromanga.net.repository

import com.zerodev.zeromanga.db.models.MangaDatabase
import com.zerodev.zeromanga.db.models.MangaFav

class MangaFavRepositoryImpl constructor (val db: MangaDatabase) : MangaFavRepository {

   override suspend fun addMangaFav(mangaFav: MangaFav) = db.getMangaFavDao().addManga(mangaFav)

    override suspend fun updateMangaFav(mangaFav: MangaFav) = db.getMangaFavDao().updateManga(mangaFav)

    override suspend fun deleteMangaFav(mangaFav: MangaFav) = db.getMangaFavDao().deleteManga(mangaFav)

    override fun getAllMangas() = db.getMangaFavDao().getAllMangas()

    override fun searchManga(query:String) = db.getMangaFavDao().seachManga(query)
}