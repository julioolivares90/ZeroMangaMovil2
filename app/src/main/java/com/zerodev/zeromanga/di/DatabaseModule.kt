package com.zerodev.zeromanga.di

import android.content.Context
import com.zerodev.zeromanga.data.local.db.MangaCacheDao
import com.zerodev.zeromanga.data.local.db.MangaFavDao
import com.zerodev.zeromanga.data.local.db.models.MangaDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(context: Context) : MangaDatabase {
        return  MangaDatabase.getDataBase(context =context )
    }

    fun provideMangaFavDao(database: MangaDatabase): MangaFavDao {
        return database.getMangaFavDao()
    }

    fun provideMangaCacheDao(database:MangaDatabase) : MangaCacheDao{
        return database.getMangaCacheDao()
    }

    single { provideDatabase(androidContext()) }
    single { provideMangaFavDao(get()) }

    single { provideDatabase(androidContext()) }
    single { provideMangaCacheDao(get()) }
}