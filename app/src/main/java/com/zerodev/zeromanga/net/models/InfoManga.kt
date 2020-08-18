package com.zerodev.zeromanga.net.models

data class InfoManga( val capitulo: List<Capitulo>,
                      val demografia: String,
                      val descripcion: String,
                      val estado: String,
                      val generos: List<String>,
                      val image: String,
                      val score: String,
                      val tipo: String,
                      val title: String)