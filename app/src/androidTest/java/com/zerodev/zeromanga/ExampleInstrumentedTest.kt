package com.zerodev.zeromanga

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.zerodev.zeromanga.data.local.db.MangaFavDao
import com.zerodev.zeromanga.data.local.db.models.MangaDatabase
import com.zerodev.zeromanga.data.local.db.models.MangaFav
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException
import kotlin.jvm.Throws

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.zerodev.zeromanga", appContext.packageName)
    }

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
    fun writeMangaInList(): Unit = runBlocking{
        val mangaFav : MangaFav = MangaFav(1,"test1","test1","test1","test1","test1","test1","test1")
        mangaFavDao.addManga(mangaFav)

        val bytitle = mangaFavDao.seachManga("test1")
        val result = bytitle.value

        result?.forEach {
            println(it.title)
        }
        val r = result?.get(0)
        assert(r == mangaFav)
    }
}