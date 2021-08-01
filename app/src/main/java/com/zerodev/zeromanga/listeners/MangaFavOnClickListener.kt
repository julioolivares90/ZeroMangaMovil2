package com.zerodev.zeromanga.listeners

import com.zerodev.zeromanga.data.remote.models.Manga

interface MangaFavOnClickListener  {
    fun OnClick(manga : Manga)
}