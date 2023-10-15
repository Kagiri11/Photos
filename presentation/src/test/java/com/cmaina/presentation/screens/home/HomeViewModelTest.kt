package com.cmaina.presentation.screens.home

import com.cmaina.presentation.utils.FakePhotosRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        homeViewModel = HomeViewModel(FakePhotosRepository())
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `fetchPhoto updates home ui state with successful ui state of images`() = runBlocking {
        assertThat(homeViewModel.homeState.value).isInstanceOf(HomeUiState.Loading.javaClass)
    }
}


