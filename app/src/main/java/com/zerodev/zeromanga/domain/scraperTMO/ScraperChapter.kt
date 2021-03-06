package com.zerodev.zeromanga.domain.scraperTMO

import android.util.Log
import com.zerodev.zeromanga.utlities.getHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Request
import org.jsoup.Jsoup

class ScraperChapter {


    suspend fun GetImagesFromChapters(urlRefer : String, url : String) : MutableList<String>{
        Log.d("urlRefer",urlRefer)
        Log.d("url capitulo leer",url)
        val newUrl = getUrlFromRedirection(urlRefer, url)

        val htmlOfPage = getHTMLFromPage(urlRefer,newUrl)
        return  GetUrlFromImages(htmlOfPage)
    }

    //retorna la url despues de la redireccion
   private  suspend fun getUrlFromRedirection(urlRefer : String, url : String) : String {

        val request = Request.Builder()
            .url(url)
            .addHeader("referer",urlRefer)
            .build()

        var newUrl = ""

        val url = withContext(Dispatchers.IO){
            getHttpClient()
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

   private  suspend fun getHTMLFromPage(urlRefer: String, url: String) : String {

        val request = Request
            .Builder()
            .url(url)
            .addHeader("referer",urlRefer)
            .addHeader("method","GET")
            .addHeader("authority","lectortmo.com")
            .addHeader("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
            .build()

         val html = withContext(Dispatchers.IO){
             try {
                 getHttpClient().newCall(request).execute().body?.string()
             }catch (ex: Exception){
                 ""
             }

         }
        return html!!
    }

    private fun ContainMainContainer(content : String) : Boolean{

        val document = Jsoup.parse(content)

         if (content.isNotEmpty()){
             try {
                 val container = document.select("#main-container").first()
                 if (container != null)
                     return true
                 return false
             }catch (ex : Exception){
                 Log.d("EROOR =>",ex.message.toString())
                 return false
             }
         }else {
             return false
         }

    }


   private  fun GetUrlFromImages(content : String) : MutableList<String> {

        var imagenes =  mutableListOf<String>()

        val document = Jsoup.parse(content)

       if (content.isNotEmpty()){
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
                   imagenes = ArrayList()
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
                   imagenes = ArrayList()
               }
           }
       }else {
           return ArrayList()
       }

       return ArrayList()
    }
}