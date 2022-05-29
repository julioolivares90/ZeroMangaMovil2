package com.zerodev.zeromanga

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.zerodev.zeromanga.di.*
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Thread.setDefaultUncaughtExceptionHandler { t, e ->
            handleUncaughtException(t,e)
        }
    }

    private  fun handleUncaughtException(thread: Thread,e : Throwable){
        Crashes.trackError(e)
    }
}