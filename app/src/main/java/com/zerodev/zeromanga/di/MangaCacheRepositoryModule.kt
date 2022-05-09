package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.data.local.db.MangaCacheDao
import com.zerodev.zeromanga.domain.repository.MangaCacheRepository
import com.zerodev.zeromanga.domain.repository.MangaCacheRepositoryImpl
import org.koin.dsl.module

val mangaCacheRepositoryModule = module {
    fun provideMangaCacheRepository(mangaCacheDao: MangaCacheDao) : MangaCacheRepository {
        return MangaCacheRepositoryImpl(mangaCacheDao)
    }

    single {
        provideMangaCacheRepository(get())
    }
}
