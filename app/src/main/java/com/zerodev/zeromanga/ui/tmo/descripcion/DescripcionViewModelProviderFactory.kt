package com.zerodev.zeromanga.ui.tmo.descripcion

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zerodev.zeromanga.domain.repository.MangaFavRepositoryImpl
import com.zerodev.zeromanga.domain.repository.MangaRepositoryImpl

class DescripcionViewModelProviderFactory (val app: Application, private val mangaFavrepository: MangaFavRepositoryImpl, private val repository: MangaRepositoryImpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DescripcionViewModel(app,mangaFavrepository,repository) as T
    }



}