package com.zerodev.zeromanga.listeners

import com.zerodev.zeromanga.net.models.Manga

interface MangaOnclickListener {
     fun onClick(manga: Manga)
}