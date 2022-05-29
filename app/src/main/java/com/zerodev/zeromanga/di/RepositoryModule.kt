package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.data.local.db.MangaCacheDao
import com.zerodev.zeromanga.data.local.db.MangaFavDao
import com.zerodev.zeromanga.data.remote.api.Api
import com.zerodev.zeromanga.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun providesRepository(api : Api) : MangaRepository{
        return MangaRepositoryImpl(api)
    }

    @Provides
    fun providesMangaFavRepository(mangaFavDao: MangaFavDao): MangaFavRepository {
        return  MangaFavRepositoryImpl(mangaFavDao)
    }

    @Provides
    fun providesMangaCacheRepository(mangaCacheDao: MangaCacheDao): MangaCacheRepository{
        return  MangaCacheRepositoryImpl(mangaCacheDao)
    }
}