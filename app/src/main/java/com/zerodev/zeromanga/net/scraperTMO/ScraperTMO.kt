package com.zerodev.zeromanga.net.scraperTMO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup


class ScraperTMO {

    //recibe el parametro url y retorna un arraylist de string que contiene una lista con todas las url de las imagenes
    suspend fun getImagesNumberFromCapitulo(url : String) = withContext(Dispatchers.IO) {

        val nuevaUrl = getDataSRC(url)
        val document = Jsoup.connect(nuevaUrl).get()
        val numberOfCaps   = arrayListOf<Int>()
        val listImagenes =  ArrayList<String>()

        document.let {

            val numberCaps = it.select("div.container:nth-child(4) > div:nth-child(1) > div:nth-child(1) > select:nth-child(2)").first()
            val options = numberCaps.getElementsByTag("option")

            options.forEach {option->
                numberOfCaps.add(option.attr("value").toInt())
            }

            numberOfCaps.forEach {number ->
                val urlFull = "$nuevaUrl/$number"
                listImagenes.add(getImagenFromCapitulo(urlFull))
                delay(3000)
            }
        }
        return@withContext listImagenes
   }

    //recibe como parametro la url del sitio para obtener una imagen del lector de TMO y retorna la url de la imagen
    suspend fun getImagenFromCapitulo(url :String) : String{
        var imagen = ""

        withContext(Dispatchers.IO){

            val document = Jsoup.connect(url).get()

            imagen = document.getElementsByTag("img")
                .first()
                .attr("src")
        }
        return imagen
    }

    //obtiene el atributo data-src de la pagina  para iniciar el escraping el atributo es la url de la pagina que contiene todos los capitulos
    suspend fun getDataSRC(url: String) = withContext(Dispatchers.IO){

        val document = Jsoup.connect(url).get()
        val dataSRC = document.select("#app > div.pbl.pbl_top.text-center.col-12.col-md-8.offset-md-2 > div")
            .first()
        val src = dataSRC.attr("data-src")

        return@withContext src
    }
}