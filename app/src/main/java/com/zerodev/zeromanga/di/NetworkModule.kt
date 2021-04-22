package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.utlities.constantes.BASE_URL
import com.zerodev.zeromanga.utlities.getHttpClient
import okhttp3.*
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

val networkModule = module {
    /*
    ejemplo con okhttpclient
    val connectTimeout : Long = 40// 20s
    val readTimeout : Long  = 40 // 20s

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single {
        val baseUrl = androidContext().getString(R.string.BASE_URL)
        provideRetrofit(get(), baseUrl)
    }
     */


    fun provideRetrofit(baseUrl:String) : Retrofit {
        val okHttpClient = getHttpClient()
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        val baseurl = BASE_URL
        provideRetrofit(baseurl)
    }
}