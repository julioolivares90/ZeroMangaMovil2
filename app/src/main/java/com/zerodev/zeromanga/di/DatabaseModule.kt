package com.zerodev.zeromanga.di

import android.app.Application
import androidx.room.Room
import com.zerodev.zeromanga.data.local.db.MangaFavDao
import com.zerodev.zeromanga.data.local.db.models.MangaDatabase
import com.zerodev.zeromanga.utlities.constantes.DATABASE_NAME
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    fun provideDatabase(application: Application) : MangaDatabase {
        return  Room.databaseBuilder(application, MangaDatabase::class.java,DATABASE_NAME)
            .build()
    }

    fun provideMangaFavDao(database: MangaDatabase): MangaFavDao {
        return database.getMangaFavDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideMangaFavDao(get()) }
}