package com.zerodev.zeromanga.utlities
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit



fun getUrlManga(urlManga : String) : String {
    return if(urlManga.startsWith("tps")) "ht${urlManga}" else urlManga
}

fun getHttpClient() : OkHttpClient{
    return OkHttpClient.Builder()
        .callTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20,TimeUnit.SECONDS)
        .addInterceptor{chain ->
            val request = chain.request()
            val newChain = chain.withConnectTimeout(20, TimeUnit.SECONDS)
            return@addInterceptor newChain.proceed(request)
        }
        .build()
}

fun AdapterString(url : String ) : String {
    if (url.endsWith("cascade")){
       val urlNew = url.replace("/cascade","/paginated")
        return urlNew
    }
    else if (url.endsWith("paginated")){
        return url
    }
    else if(!url.endsWith("cascade")){
        val newUrl= "$url/paginated"
        return newUrl
    }
    else if (!url.endsWith("paginated")){
        val newUrl = "$url/paginated"
        return newUrl
    }
    return url
}