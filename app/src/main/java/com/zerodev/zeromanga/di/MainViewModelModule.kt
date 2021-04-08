package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel {
        MainViewModel(repository = get())
    }
}