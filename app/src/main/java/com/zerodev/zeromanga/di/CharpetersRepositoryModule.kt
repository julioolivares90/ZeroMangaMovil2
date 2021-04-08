package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.net.repository.CharpetersRepository
import com.zerodev.zeromanga.net.repository.ChaptersRepositoryImpl
import org.koin.dsl.module

val charpetersRepositoryModule = module {
    fun provideCharpetersRepository() : CharpetersRepository {
        return ChaptersRepositoryImpl()
    }

    single {
        provideCharpetersRepository()
    }
}