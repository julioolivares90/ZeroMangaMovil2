package com.zerodev.zeromanga.di

import android.content.Context
import com.zerodev.zeromanga.data.local.db.MangaCacheDao
import com.zerodev.zeromanga.data.local.db.MangaFavDao
import com.zerodev.zeromanga.data.local.db.models.MangaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMangaDatabase(@ApplicationContext appcontext: Context): MangaDatabase {
        return MangaDatabase.getDataBase(appcontext)
    }
    @Provides
    fun provideMangaFavDao(database: MangaDatabase): MangaFavDao {
       return database.getMangaFavDao()
    }

    @Provides
    fun provideMangaCacheDao(database: MangaDatabase) : MangaCacheDao {
        return  database.getMangaCacheDao()
    }
}