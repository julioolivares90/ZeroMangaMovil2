package com.zerodev.zeromanga.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.zerodev.zeromanga.data.local.db.MangaCacheDao
import com.zerodev.zeromanga.data.local.db.models.MangaCache
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class MangaCacheRepositoryImpl constructor(private val dao : MangaCacheDao) : MangaCacheRepository {


    override suspend fun getMangasChache(demography: String): LiveData<MutableList<MangaCache>> {
        return dao.getMangas(demography).asLiveData()
    }

    override suspend fun AddMangaCahce(mangaCache: MangaCache) {
         dao.insert(mangaCache)
    }

    override suspend fun findMangaByTitle(title: String): LiveData<MangaCache> {
         return dao.getMangasByTitle(title).asLiveData()
    }


}