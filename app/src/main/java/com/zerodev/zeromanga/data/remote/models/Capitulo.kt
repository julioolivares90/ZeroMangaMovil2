package com.zerodev.zeromanga.data.remote.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Capitulo(val Title: String,
                    val UrlLeer: String) : Parcelable