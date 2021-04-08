package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.ui.favoritos.FavoritosViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritosViewModelModule = module {
    viewModel {
        FavoritosViewModel(get())
    }
}