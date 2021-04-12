package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.data.local.db.MangaFavDao
import com.zerodev.zeromanga.data.local.db.models.MangaDatabase
import com.zerodev.zeromanga.domain.repository.MangaFavRepository
import com.zerodev.zeromanga.domain.repository.MangaFavRepositoryImpl
import org.koin.dsl.module

val mangaFavRepositoryModule = module {
    fun provideMangaFavRepository(mangaDatabase : MangaDatabase) : MangaFavRepository {
        return MangaFavRepositoryImpl(mangaDatabase)
    }

    single {
        provideMangaFavRepository(get())
    }
}