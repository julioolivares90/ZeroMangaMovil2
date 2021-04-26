package com.zerodev.zeromanga.data.remote.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoManga(val capitulo: List<Capitulo>,
                     val demografia: String,
                     val descripcion: String,
                     val estado: String,
                     val generos: List<String>,
                     val image: String,
                     val score: String,
                     val tipo: String,
                     val title: String) : Parcelable