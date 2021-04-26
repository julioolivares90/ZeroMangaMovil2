package com.zerodev.zeromanga.domain.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zerodev.zeromanga.data.remote.models.ResponseCapitulos
import com.zerodev.zeromanga.data.remote.models.ResponseManga
import com.zerodev.zeromanga.utlities.getHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class ChapterRepositoryImpl : CharpetersRepository {
    override suspend fun GetImagesFromChapters(
        url: String,
        urlRefer: String
    ): ResponseManga<MutableList<String>> {


        val urlCapitulo = "https://tumangaonlineapi.herokuapp.com/api/Mangas/Manga-paginas?urlRefer=${urlRefer}&urlCapitulo=$url"
        val capitulos = mutableListOf<String>()

        val okHttpClient = getHttpClient()

        val request = Request.Builder().url(url = urlCapitulo)
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()

                val gson = GsonBuilder().create()

               val result = gson.fromJson(json,ResponseCapitulos::class.java)
                capitulos.addAll(result.data)
            }

        })

        return if (capitulos.isNotEmpty()){
            ResponseManga.Success(capitulos)
        }else {
            ResponseManga.Error(Exception("Ocurrio un error!!"))
        }

    }
}