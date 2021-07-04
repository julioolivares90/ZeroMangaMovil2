package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.ui.tmo.lector.LectorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val lectorViewModelModule = module {
    viewModel {
        LectorViewModel(get())
    }
}