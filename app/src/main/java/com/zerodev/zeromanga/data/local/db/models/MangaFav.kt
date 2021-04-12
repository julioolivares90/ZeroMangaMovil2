package com.zerodev.zeromanga.data.local.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "mangas_favoritos")
data class MangaFav(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title : String,
    val imagen : String,
    val score: String,
    val tipo: String,
    val demografia: String,
    val descripcion: String,
    val url : String,

)
