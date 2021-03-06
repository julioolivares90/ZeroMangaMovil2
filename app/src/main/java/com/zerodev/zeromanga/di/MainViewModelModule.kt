package com.zerodev.zeromanga.di

import com.zerodev.zeromanga.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainViewModelModule = module {
    viewModel {
        MainViewModel(repository = get(),androidContext())
    }
}