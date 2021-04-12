package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.data.remote.api.Api
import com.zerodev.zeromanga.domain.repository.MangaRepository
import com.zerodev.zeromanga.domain.repository.MangaRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val mangaRepositoryModule = module {
    fun provideMangaRepository(api: Api) : MangaRepository {
        return  MangaRepositoryImpl(api)
    }

    single<MangaRepository> {provideMangaRepository(api = get())}
}