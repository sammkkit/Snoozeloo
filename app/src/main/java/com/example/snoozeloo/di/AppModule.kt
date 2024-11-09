package com.example.snoozeloo.di

import androidx.lifecycle.get
import com.example.snoozeloo.Presentation.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
val AppModule = module {
    viewModel {
        MainViewModel()
    }
}