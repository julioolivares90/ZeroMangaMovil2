package com.zerodev.zeromanga

import com.zerodev.zeromanga.data.remote.api.Api
import com.zerodev.zeromanga.data.remote.api.RetrofitSingleton
import com.zerodev.zeromanga.domain.repository.MangaFavRepositoryImpl
import com.zerodev.zeromanga.domain.repository.MangaRepository
import com.zerodev.zeromanga.domain.repository.MangaRepositoryImpl
import org.junit.Test

import org.junit.Assert.*

import com.zerodev.zeromanga.domain.scraperTMO.ScraperChapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val scraper : ScraperChapter = ScraperChapter()
    val api = RetrofitSingleton.buildService(Api::class.java)
    val repository : MangaRepository = MangaRepositoryImpl(api)
    val urlReferTest2 = "https://lectortmo.com/library/manhwa/51920/mal-maestro"
    val urlTest2 = "https://mangalong.com/news/5f196aabc786a/cascade"

    val urlReferTest1 = "https://lectortmo.com/library/manga/16476/Kenja-no-Mago"
    val urlTest1 = "https://lectortmo.com/view_uploads/638593"


    @Test
    fun `comprueba repository` () = runBlocking {

       val result = api.getInfoManga("https://lectortmo.com/view_uploads/486011")

        if (result.statusCode == 200){
            result.data.capitulo.forEach {
                print("titulo : ${it.Title} - url leer: ${it.UrlLeer}")
            }
        }
        print("Codigo error ${result.statusCode}")
    }
    @Test
    fun `comprueba si existe main container en la pagina solicitada`() = runBlocking{
        var newUrl = scraper.getUrlFromRedirection(urlReferTest1,urlTest1)
        var htmlOfPage = scraper.getHTMLFromPage(urlRefer = urlReferTest1,url = newUrl)

        val existMainContainer = scraper.ContainMainContainer(htmlOfPage)

        if (existMainContainer)
            print(true)
        else
            print(false)
    }
   /*
    @Test
    fun `obtener lista de imagenes de un manga`()= runBlocking{
        var newUrl = scraper.getUrlFromRedirection(urlReferTest1,urlTest1)
        var htmlOfPage = scraper.getHTMLFromPage(urlRefer = urlReferTest1,url = newUrl)

        //print("HTML of Page $htmlOfPage")
        var imagenes = scraper.GetUrlFromImages(htmlOfPage)

        for (img in imagenes)
            print(img)
    }
    */

}