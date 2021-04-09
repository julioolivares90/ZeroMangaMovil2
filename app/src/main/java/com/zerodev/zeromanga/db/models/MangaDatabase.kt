package com.zerodev.zeromanga.db.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zerodev.zeromanga.db.MangaFavDao

@Database(
    entities = [MangaFav::class],
    version = 1
)
abstract class MangaDatabase : RoomDatabase() {

    abstract fun getMangaFavDao(): MangaFavDao

   /*
    companion object {
        @Volatile
        private var instanse : MangaDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instanse?: synchronized(LOCK){
            instanse?:
            createDatabse(context).also {
                instanse = it
            }
        }

        private fun createDatabse(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MangaDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
    */
}