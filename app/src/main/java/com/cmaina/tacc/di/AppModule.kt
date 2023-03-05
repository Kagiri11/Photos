package com.cmaina.tacc.di

import com.cmaina.tacc.screens.list.ListViewModel
import org.koin.dsl.module

val AppModule = module {
    single { ListViewModel(get()) }
}
