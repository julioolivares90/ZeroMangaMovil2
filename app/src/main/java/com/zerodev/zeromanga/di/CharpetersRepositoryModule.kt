package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.domain.repository.CharpetersRepository
import com.zerodev.zeromanga.domain.repository.ChaptersRepositoryImpl
import org.koin.dsl.module

val charpetersRepositoryModule = module {
    fun provideCharpetersRepository() : CharpetersRepository {
        return ChaptersRepositoryImpl()
    }

    single {
        provideCharpetersRepository()
    }
}