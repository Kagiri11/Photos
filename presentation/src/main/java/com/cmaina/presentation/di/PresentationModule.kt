package com.cmaina.presentation.di

import com.cmaina.presentation.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::HomeViewModel)
}
