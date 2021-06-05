package com.zerodev.zeromanga

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zerodev.zeromanga.data.remote.api.Api
import com.zerodev.zeromanga.data.remote.api.RetrofitSingleton
import com.zerodev.zeromanga.data.remote.models.ResponseManga
import com.zerodev.zeromanga.domain.repository.MangaRepositoryImpl
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestREpositories {
    private lateinit var api : Api

    private lateinit var repository : MangaRepositoryImpl
    @Before
    fun getApi(){
        api = RetrofitSingleton.buildService(Api::class.java)
        repository = MangaRepositoryImpl(api)
    }

    @Test
    fun TestRepository() = runBlocking {
        val data = repository.getMangaData()

        when(data){
            is ResponseManga.Success -> {
                val mangasSeinen = data.data.mangasSeinen
                val mangasPopulares = data.data.mangasPopulares

                for (m in mangasSeinen){
                    print(m.title)
                }

                mangasPopulares.forEach {
                    print(it.title)
                }
            }
            else -> print("Error")
        }
    }
}