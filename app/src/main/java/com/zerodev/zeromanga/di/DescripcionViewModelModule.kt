package com.zerodev.zeromanga.di

import android.app.Application
import com.zerodev.zeromanga.domain.repository.MangaFavRepository
import com.zerodev.zeromanga.domain.repository.MangaRepository
import com.zerodev.zeromanga.ui.tmo.descripcion.DescripcionViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val descripcionViewModelModule = module {
    viewModel {
        DescripcionViewModel(androidApplication(),get(),get())
    }
}