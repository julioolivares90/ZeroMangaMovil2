package com.zerodev.zeromanga.utlities
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

fun getHttpClient() : OkHttpClient{
    return OkHttpClient.Builder()
        .callTimeout(2000L, TimeUnit.MILLISECONDS)
        .readTimeout(10000,TimeUnit.MILLISECONDS)
        .addInterceptor{chain ->
            val request = chain.request()
            val newChain = chain.withConnectTimeout(10000, TimeUnit.MILLISECONDS)
            return@addInterceptor newChain.proceed(request)
        }
        .build()
}
fun GetImagenURL(url : String) : String{

    return ""
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