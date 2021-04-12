package com.zerodev.zeromanga.listeners

import com.zerodev.zeromanga.data.remote.models.Manga

interface MangaOnclickListener {
     fun onClick(manga: Manga)
}