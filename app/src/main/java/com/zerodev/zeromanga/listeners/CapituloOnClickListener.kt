package com.zerodev.zeromanga.listeners

import com.zerodev.zeromanga.net.models.Capitulo

interface CapituloOnClickListener {
    fun onClick(capitulo: Capitulo)
}