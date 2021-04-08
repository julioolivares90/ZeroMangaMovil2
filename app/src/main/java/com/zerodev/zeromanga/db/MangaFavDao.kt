package com.zerodev.zeromanga.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zerodev.zeromanga.db.models.MangaFav

@Dao
interface MangaFavDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addManga(mangaFav: MangaFav)

    @Update
    suspend fun updateManga(mangaFav: MangaFav)

    @Delete
    suspend fun deleteManga(mangaFav: MangaFav)

    @Query("SELECT * from mangas_favoritos ORDER BY id DESC")
    fun getAllMangas(): LiveData<List<MangaFav>>

    @Query("SELECT * FROM mangas_favoritos where title like :query or descripcion like :query")
    fun seachManga(query : String) : LiveData<List<MangaFav>>
}