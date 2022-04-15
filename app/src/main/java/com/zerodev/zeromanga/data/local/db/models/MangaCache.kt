package com.zerodev.zeromanga.data.local.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mangaCache")
data class MangaCache(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title : String,
    val mangaUrl : String,
    val type: String,
    val demography : String,
    val score : String,
    val mangaImagen : String
)