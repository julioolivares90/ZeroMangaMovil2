package com.zerodev.zeromanga

import org.junit.Test

import org.junit.Assert.*

import com.zerodev.zeromanga.net.scraperTMO.ScraperChapter
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
    val urlReferTest2 = "https://lectortmo.com/library/manhwa/51920/mal-maestro"
    val urlTest2 = "https://mangalong.com/news/5f196aabc786a/cascade"

    val urlReferTest1 = "https://lectortmo.com/library/manga/16476/Kenja-no-Mago"
    val urlTest1 = "https://lectortmo.com/view_uploads/638593"


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
    @Test
    fun `obtener lista de imagenes de un manga`()= runBlocking{
        var newUrl = scraper.getUrlFromRedirection(urlReferTest1,urlTest1)
        var htmlOfPage = scraper.getHTMLFromPage(urlRefer = urlReferTest1,url = newUrl)

        //print("HTML of Page $htmlOfPage")
        var imagenes = scraper.GetUrlFromImages(htmlOfPage)

        for (img in imagenes)
            print(img)
    }

}