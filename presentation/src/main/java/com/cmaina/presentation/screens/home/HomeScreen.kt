package com.cmaina.presentation.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.cmaina.presentation.R
import com.cmaina.presentation.components.photoscards.PhotoCardItem
import com.cmaina.presentation.components.photostext.FotosTitleText
import com.cmaina.presentation.screens.lazyItems
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    navController: NavController
) {
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()
    val uiState = viewModel.homeUiState.collectAsStateWithLifecycle().value

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (title, photosGrid) = createRefs()

        FotosTitleText(
            text = stringResource(R.string.home_title_text),
            textColor = MaterialTheme.colors.onPrimary,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, margin = 20.dp)
                start.linkTo(parent.start, margin = 15.dp)
            }
        )

        when {
            uiState.isLoading -> {
            }
            uiState.errorMessage != null -> {}
            uiState.pagedPhotos != null -> {
                val photos = uiState.pagedPhotos.collectAsLazyPagingItems()
                LazyVerticalStaggeredGrid(
                    modifier = Modifier
                        .constrainAs(photosGrid) {
                            top.linkTo(title.bottom, margin = 10.dp)
                            bottom.linkTo(parent.bottom)
                            height = Dimension.fillToConstraints
                        }
                        .fillMaxWidth(),
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(1.dp),
                    state = lazyStaggeredGridState
                ) {
                    lazyItems(photos) { pic ->
                        PhotoCardItem(
                            blurHash = pic?.blurHash ?: "",
                            imageUrl = pic?.domainUrls?.small ?: ""
                        ) {
                            navController.navigate("photo_detail_screen/${pic?.id ?: ""}")
                        }
                    }
                }
            }
        }
    }
}
