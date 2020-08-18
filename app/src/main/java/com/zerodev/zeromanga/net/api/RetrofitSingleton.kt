package com.zerodev.zeromanga.net.api

import com.zerodev.zeromanga.utlities.constantes.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSingleton {
    private val build = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory
            .create())
    private val retrofit = build.build()

    fun <T> buildService(serviceType : Class<T>) : T= retrofit.create(serviceType)
}