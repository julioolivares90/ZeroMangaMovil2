package com.zerodev.zeromanga.domain.repository
import com.zerodev.zeromanga.data.local.db.MangaFavDao
import com.zerodev.zeromanga.data.local.db.models.MangaFav

class MangaFavRepositoryImpl constructor (private val dao: MangaFavDao) : MangaFavRepository {

   override suspend fun addMangaFav(mangaFav: MangaFav) = dao.addManga(mangaFav)

    override suspend fun updateMangaFav(mangaFav: MangaFav) = dao.updateManga(mangaFav)

    override suspend fun deleteMangaFav(mangaFav: MangaFav) = dao.deleteManga(mangaFav)

    override fun getAllMangas() = dao.getAllMangas()

    override fun searchManga(query:String) = dao.seachManga(query)
}