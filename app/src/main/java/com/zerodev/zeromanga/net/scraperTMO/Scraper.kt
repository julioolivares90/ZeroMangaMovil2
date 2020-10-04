package com.zerodev.zeromanga.net.scraperTMO

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class Scraper {
    suspend fun getDataSRC(url : String,header : String?=null) = withContext(Dispatchers.IO) {
        val headerResult = header

        headerResult.let {
            val headers = mapOf<String,String>("cf-request-id" to headerResult!!, ":authority:" to "lectortmo.com")

            val document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36")
                .headers(headers)
        }
        val document = Jsoup.connect(url)
            .userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
            .get()

        Log.d("HTML BODY => ",document.html())
        val dataSRC = document.select("#app > div.pbl > div.OUTBRAIN").first()

        val src = dataSRC.attr("data-src")
        return@withContext src
    }
}