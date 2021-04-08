package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.db.MangaFavDao
import com.zerodev.zeromanga.db.models.MangaDatabase
import com.zerodev.zeromanga.net.repository.MangaFavRepository
import com.zerodev.zeromanga.net.repository.MangaFavRepositoryImpl
import org.koin.dsl.module

val mangaFavRepositoryModule = module {
    fun provideMangaFavRepository(mangaDatabase : MangaDatabase) : MangaFavRepository{
        return MangaFavRepositoryImpl(mangaDatabase)
    }

    single {
        provideMangaFavRepository(get())
    }
}