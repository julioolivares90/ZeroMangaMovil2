package com.zerodev.zeromanga.data.remote.models

data
class MangaData (val statusCode : Int,
                 val mangasPopulares: MutableList<Manga>,
                 val mangasSeinen : MutableList<Manga>,
                 val errorMessage : String?) {
}