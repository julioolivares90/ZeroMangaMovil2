package com.zerodev.zeromanga.domain.scraperTMO

import android.os.Build
import android.os.Debug
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import java.time.Duration

class ScraperChapter {



    @RequiresApi(Build.VERSION_CODES.O)
    fun getInstanceOkHttp() : OkHttpClient {
        val okHttp = OkHttpClient
            .Builder()
            .connectTimeout(Duration.ofMillis(10000))
            .build()

        return okHttp
    }
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun GetImagesFromChapters(urlRefer : String, url : String) : MutableList<String>{
        Log.d("urlRefer",urlRefer)
        Log.d("url capitulo leer",url)
        val newUrl = getUrlFromRedirection(urlRefer, url)

        val htmlOfPage = getHTMLFromPage(urlRefer,newUrl)
        return  GetUrlFromImages(htmlOfPage)
    }

    //retorna la url despues de la redireccion
     @RequiresApi(Build.VERSION_CODES.O)
     suspend fun getUrlFromRedirection(urlRefer : String, url : String) : String{
        val request = Request.Builder()
            .url(url)
            .addHeader("referer",urlRefer)
            .build()

        var newUrl = ""
        val url = withContext(Dispatchers.IO){
            getInstanceOkHttp()
                .newCall(request).execute().request.url.toString()
        }

        if (url.contains("/paginated") || url.contains("/paginated/1")){
            newUrl = url.replace("/paginated","/cascade")
            if (newUrl.contains("/cascade/1"))
                newUrl = newUrl.replace("/cascade/1","cascade")
        }
        newUrl = url
        return newUrl

    }

     @RequiresApi(Build.VERSION_CODES.O)
     suspend fun getHTMLFromPage(urlRefer: String, url: String) : String {

        val request = Request
            .Builder()
            .url(url)
            .addHeader("referer",urlRefer)
            .addHeader("method","GET")
            .addHeader("authority","lectortmo.com")
            .addHeader("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
            .build()

         val html = withContext(Dispatchers.IO){
             getInstanceOkHttp().newCall(request).execute().body?.string()
         }
        return html!!
    }

     fun ContainMainContainer(content : String) : Boolean{
        val document = Jsoup.parse(content)
        try {
            val container = document.select("#main-container").first()
            if (container != null)
                return true
            return false
        }catch (ex : Exception){
            Log.d("EROOR =>",ex.message.toString())
            return false
        }
    }


     fun GetUrlFromImages(content : String) : MutableList<String> {
        val imagenes =  mutableListOf<String>()

        val document = Jsoup.parse(content)
        if (ContainMainContainer(content)){
            try {
                val divMain = document.select("#main-container")
                val imagenesDiv = divMain.select(".img-container")
                for (i in imagenesDiv){
                    val imagen = i.select("img").attr("data-src")
                    imagen.let {
                        imagenes.add(it)
                    }
                }
            }catch (ex : Exception){
                //Log.d("Error => ",ex.message.toString())
                return imagenes
            }
        }else {
            try {
                val divViewContainer = document.select("#viewer-container")
                val divImagenes = divViewContainer.select("viewer-image-container")
                for (item in divImagenes){
                    val imagen = item.getElementById("img").attr("data-src")
                    imagenes.add(imagen)
                }
            }catch (ex : Exception){
                Log.d("ERROR => ",ex.message.toString())
                return imagenes
            }
        }
        return imagenes
    }
}