package com.zerodev.zeromanga.utlities
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit





fun getHttpClient() : OkHttpClient{
    return OkHttpClient.Builder()
        .callTimeout(20, TimeUnit.SECONDS)
        .readTimeout(40,TimeUnit.SECONDS)
        .addInterceptor{chain ->
            val request = chain.request()
            val newChain = chain.withConnectTimeout(40, TimeUnit.SECONDS)
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