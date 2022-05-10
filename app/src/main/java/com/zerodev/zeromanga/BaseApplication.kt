package com.zerodev.zeromanga

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.zerodev.zeromanga.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AppCenter.start(
            this, "",
            Analytics::class.java, Crashes::class.java
        )

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

        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            handleUncaughtException(t,e)
        }
    }

    private  fun handleUncaughtException(thread: Thread,e : Throwable){
        Crashes.trackError(e)
    }
}