package com.zerodev.zeromanga
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zerodev.zeromanga.data.local.db.MangaFavDao
import com.zerodev.zeromanga.data.local.db.models.MangaDatabase
import com.zerodev.zeromanga.data.local.db.models.MangaFav
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class PruebaRoom {
    private lateinit var mangaFavDao: MangaFavDao

    private lateinit var db : MangaDatabase

    @Before
    fun createDb(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            MangaDatabase::class.java
        ).build()

        mangaFavDao = db.getMangaFavDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeMangaInList() = runBlocking{
        val mangaFav : MangaFav = MangaFav(1,"test1","test1","test1","test1","test1","test1","test1")
        mangaFavDao.addManga(mangaFav)

        val bytitle = mangaFavDao.seachManga("test1")
        val result = bytitle.value?.get(0)
        assert(result == mangaFav)
    }
}