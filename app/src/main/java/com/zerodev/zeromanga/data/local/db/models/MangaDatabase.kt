package com.zerodev.zeromanga.data.local.db.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.zerodev.zeromanga.data.local.db.MangaCacheDao
import com.zerodev.zeromanga.data.local.db.MangaFavDao
import com.zerodev.zeromanga.utlities.constantes.DATABASE_NAME


@Database(
    entities = [MangaFav::class,MangaCache::class],
    version = 2
)
abstract class MangaDatabase : RoomDatabase() {

    abstract fun getMangaFavDao(): MangaFavDao
    abstract fun getMangaCacheDao():MangaCacheDao

    companion object {
        //para cuando se actualiza la Base
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE mangaCache (id INTEGER not null primary key AUTOINCREMENT ,title text not null,mangaUrl text not null,type text not null,demography text not null , score text not null,mangaImagen text not null)")
            }
        }

        @Volatile
        private var INSTANCE : MangaDatabase? = null

        fun getDataBase(context: Context) : MangaDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MangaDatabase::class.java,
                    DATABASE_NAME
                ).addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                instance
            }
        }
    }

}