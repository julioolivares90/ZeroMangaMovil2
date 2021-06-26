package com.zerodev.zeromanga.data.remote.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoManga(val capitulos: List<Capitulo>,
                     val demografia: String,
                     val description: String,
                     val estado: String,
                     val generos: List<String>,
                     val imageUrl: String,
                     val score: String,
                     val tipo: String,
                     val title: String) : Parcelable