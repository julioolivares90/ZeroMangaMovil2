package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.net.api.Api
import com.zerodev.zeromanga.net.repository.MangaFavRepositoryImpl
import com.zerodev.zeromanga.net.repository.MangaRepository
import com.zerodev.zeromanga.net.repository.MangaRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    fun provideMangaRepository(api : Api) : MangaRepository {
        return MangaRepositoryImpl(api)
    }

    single { provideMangaRepository(get()) }
}