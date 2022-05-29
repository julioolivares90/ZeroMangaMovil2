package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.utlities.constantes.BASE_URL
import com.zerodev.zeromanga.utlities.getHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule  {

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        val okHttpClient = getHttpClient()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}