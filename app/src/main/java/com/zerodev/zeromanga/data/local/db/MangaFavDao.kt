package com.zerodev.zeromanga.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zerodev.zeromanga.data.local.db.models.MangaFav

@Dao
interface MangaFavDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addManga(mangaFav: MangaFav) : Long

    @Update
    suspend fun updateManga(mangaFav: MangaFav)

    @Delete
    suspend fun deleteManga(mangaFav: MangaFav)

    @Query("SELECT * from mangas_favoritos ORDER BY id DESC")
    fun getAllMangas(): LiveData<List<MangaFav>>

    @Query("SELECT * FROM mangas_favoritos where title like :query or descripcion like :query")
    fun seachManga(query : String) : LiveData<List<MangaFav>>


    @Query("SELECT * from mangas_favoritos where title == :title")
    fun findMangaByTitle(title: String) : LiveData<MangaFav>
}