package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.data.remote.api.Api
import com.zerodev.zeromanga.domain.repository.MangaFavRepositoryImpl
import com.zerodev.zeromanga.domain.repository.MangaRepository
import com.zerodev.zeromanga.domain.repository.MangaRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    fun provideMangaRepository(api : Api) : MangaRepository {
        return MangaRepositoryImpl(api)
    }

    single { provideMangaRepository(get()) }
}