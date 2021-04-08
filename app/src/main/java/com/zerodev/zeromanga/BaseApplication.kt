package com.zerodev.zeromanga

import android.app.Application
import com.zerodev.zeromanga.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                apiModule,
                mainViewModelModule,
                networkModule,
                databaseModule,
                charpetersRepositoryModule,
                busquedaViewModelModule,
                descripcionViewModelModule,
                favoritosViewModelModule,
                lectorViewModelModule,
                mangaFavRepositoryModule,
                mangaRepositoryModule,

            )
        }
    }
}