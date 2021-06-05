package com.zerodev.zeromanga

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zerodev.zeromanga.data.remote.api.Api
import com.zerodev.zeromanga.data.remote.api.RetrofitSingleton
import com.zerodev.zeromanga.utlities.DoesNetworkHaveInternet
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestApi {

    private lateinit var api : Api



    @Before
    fun getApi(){
        api = RetrofitSingleton.buildService(Api::class.java)
    }

    @Test
    fun getAllMangasPopulares() = runBlocking{
        val mangas = api.getAllMangasPopulares(1)

        if (mangas.data.isEmpty()){
            print("Nose encontraron Mangas")
        }
        else {
            for (manga in mangas.data){
                print(manga.title)
                print(manga.type)
            }
        }

    }

    @Test
    fun getAllData() = runBlocking {
        val data = api.GetData()

        if (data.body() != null){
            val mangasSeinen = data.body()!!.mangasSeinen
            val mangasPopulares = data.body()!!.mangasPopulares

            mangasSeinen.forEach {manga->
                print(manga.title)
            }

            mangasPopulares.forEach {manga->
                print(manga.title)
            }
        }
        else {
            assert(data.isSuccessful)
        }
    }



}