package com.zerodev.zeromanga.net.scraperTMO

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class Scraper {

    suspend fun getDataSRC(url : String,cookies : Map<String,String?>?= null) = withContext(Dispatchers.IO) {
        val headerResult = cookies

        var src : String = ""
        headerResult?.let {
            val headers = mapOf("tumangaonline_session" to headerResult["tu_manga_online_session"], ":authority:" to "lectortmo.com")

            Log.d("Cookies",cookies.toString())

            val document = Jsoup.connect(url)
                .cookies(cookies)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36")
                .headers(headers).get()

            document?.let {
                val dataSRC = it.select("#app > div.pbl > div.OUTBRAIN\"").first()
                 src = dataSRC.attr("data-src")

            }
        }

        /*
        val document = Jsoup.connect(url)
            .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
            .get()

        Log.d("HTML BODY => ",document.html())
        val dataSRC = document.select("#app > div.pbl > div.OUTBRAIN").first()

         src = dataSRC.attr("data-src")

*/
        return@withContext src
    }

    suspend fun GetPagainasForManga(url : String,cookies : Map<String,String?>?= null)  = withContext(Dispatchers.IO){
        //lista que almacenara las imagenes de los capitulos de los mangas
        val listaDeCapitulosImagenes = mutableListOf<String>()

        if (url.contains("/paginated")){
            val newUrl = url.replace("/paginated","/cascade")
            listaDeCapitulosImagenes.addAll(getImagenes(url = newUrl,cookies = cookies))
        }else {
            listaDeCapitulosImagenes.addAll(getImagenes(url,cookies))
        }
        return@withContext listaDeCapitulosImagenes
    }

   private suspend fun getImagenes(url : String,cookies : Map<String,String?>?= null) = withContext(Dispatchers.IO){

       val imagenes = mutableListOf<String>()

       val header = cookies?.get("tumangaonline_session")

       header.let {
           val headers = mapOf("tumangaonline_session" to it, ":authority:" to "lectortmo.com")
           val document = Jsoup.connect(url)
               .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36")
               .headers(headers)
               .cookies(cookies)
               .get()

           val divPrincipal = document?.select("#app > #main-container div.img-container")

           divPrincipal?.let {divs->

               for (div in  divs){
                   val img = div.select("img")
                   val pagina = img.attr("data-src")
                   Log.d("PAGINA = >",pagina)
                   imagenes.add(pagina)
               }
           }
           return@withContext imagenes
       }
   }
}