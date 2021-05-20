package com.zerodev.zeromanga.data.remote.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Capitulo(val name: String,
                    val urlLeer: String) : Parcelable