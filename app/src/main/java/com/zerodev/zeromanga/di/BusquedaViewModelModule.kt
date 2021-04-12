package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.domain.repository.MangaRepository
import com.zerodev.zeromanga.ui.busqueda.BusquedaViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val busquedaViewModelModule = module {
    viewModel {
        BusquedaViewModel(get())
    }
}