package com.zerodev.zeromanga.data.local.db.models

import android.content.Context
import androidx.navigation.dynamicfeatures.Constants
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zerodev.zeromanga.data.local.db.MangaFavDao
import com.zerodev.zeromanga.utlities.constantes.DATABASE_NAME

@Database(
    entities = [MangaFav::class],
    version = 1
)
abstract class MangaDatabase : RoomDatabase() {

    abstract fun getMangaFavDao(): MangaFavDao


    companion object {
        @Volatile
        private var INSTANCE : MangaDatabase? = null

        fun getDataBase(context: Context) : MangaDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MangaDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }

        /*
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
        * */
    }

}