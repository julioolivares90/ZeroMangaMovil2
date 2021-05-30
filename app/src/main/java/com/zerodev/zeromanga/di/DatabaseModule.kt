package com.zerodev.zeromanga.di

import android.content.Context
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

    single { provideDatabase(androidContext()) }
    single { provideMangaFavDao(get()) }
}