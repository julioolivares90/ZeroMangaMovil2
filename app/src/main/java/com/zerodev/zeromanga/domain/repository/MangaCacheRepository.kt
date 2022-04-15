package com.zerodev.zeromanga.domain.repository

import androidx.constraintlayout.helper.widget.Flow
import androidx.lifecycle.LiveData
import com.zerodev.zeromanga.data.local.db.models.MangaCache

interface MangaCacheRepository {

    suspend fun getMangasChache(demography : String) : LiveData<MutableList<MangaCache>>

    suspend fun AddMangaCahce(mangaCache: MangaCache): Long

    suspend fun findMangaByTitle(title : String) : LiveData<MangaCache>
}