package com.zerodev.zeromanga.listeners

import com.zerodev.zeromanga.data.remote.models.Capitulo

interface CapituloOnClickListener {
    fun onClick(capitulo: Capitulo)
}