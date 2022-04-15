package com.zerodev.zeromanga.data.local.db

import androidx.room.*
import com.zerodev.zeromanga.data.local.db.models.MangaCache
import kotlinx.coroutines.flow.Flow

interface MangaCacheDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE )
    suspend fun insert(mangaCache: MangaCache)

    @Update
    suspend fun update(mangaCache: MangaCache)

    @Delete
    suspend fun delete(mangaCache: MangaCache)

    @Query("SELECT * FROM mangaCache WHERE id=:id")
    suspend fun getByID(id : Int): Flow<MangaCache>

    @Query("SELECT * FROM mangaCache WHERE demography=:demography")
    suspend fun getMangas(demography:String): Flow<MutableList<MangaCache>>
}