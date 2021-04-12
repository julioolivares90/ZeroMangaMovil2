package com.zerodev.zeromanga.ui.favoritos

import androidx.lifecycle.ViewModel
import com.zerodev.zeromanga.data.local.db.MangaFavDao
import com.zerodev.zeromanga.domain.repository.MangaFavRepository

class FavoritosViewModel  (private val mangaFavRepository: MangaFavRepository) : ViewModel() {

}