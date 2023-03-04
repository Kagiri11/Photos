package com.cmaina.tacc.di

import com.cmaina.tacc.screens.details.DetailsViewModel
import com.cmaina.tacc.screens.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {
    viewModel { DetailsViewModel() }
    viewModel { ListViewModel(get()) }
}
