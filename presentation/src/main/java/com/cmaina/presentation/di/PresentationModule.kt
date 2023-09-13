package com.cmaina.presentation.di

import com.cmaina.presentation.screens.home.HomeViewModel
import com.cmaina.presentation.screens.photodetails.PhotoDetailsViewModel
import com.cmaina.presentation.screens.search.SearchViewModel
import com.cmaina.presentation.screens.settings.SettingsViewModel
import com.cmaina.presentation.screens.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::PhotoDetailsViewModel)
    viewModelOf(::UserViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::SettingsViewModel)
}
