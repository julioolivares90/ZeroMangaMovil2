package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.net.api.Api
import com.zerodev.zeromanga.net.repository.MangaRepository
import com.zerodev.zeromanga.net.repository.MangaRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val mangaRepositoryModule = module {
    fun provideMangaRepository(api: Api) : MangaRepository {
        return  MangaRepositoryImpl(api)
    }

    single<MangaRepository> {provideMangaRepository(api = get())}
}