package com.cmaina.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.cmaina.presentation.components.photoscards.PhotoCardItem
import com.cmaina.presentation.components.photostext.FotosTitleText
import com.cmaina.presentation.screens.items
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.ui.theme.FotosWhite
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getViewModel(),
    navController: NavController
) {
    val myPictures = viewModel.pics.observeAsState().value?.collectAsLazyPagingItems()
    val searchText = viewModel.searchString.collectAsState().value
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(FotosWhite)
    ) {
        val (title, searchBar, fotosGrid) = createRefs()

        FotosTitleText(
            text = "Explore",
            textColor = FotosBlack,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, margin = 20.dp)
                start.linkTo(parent.start, margin = 10.dp)
            }
        )
        /*TextField(
            value = searchText,
            onValueChange = {
                viewModel.searchString.value = it
            },
            modifier = Modifier.constrainAs(searchBar) {
                top.linkTo(title.bottom, margin = 10.dp)
                end.linkTo(parent.end, margin = 10.dp)
            }
        )*/
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(1.dp),
            modifier = Modifier
                .constrainAs(fotosGrid) {
                    top.linkTo(title.bottom, margin = 10.dp)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
                .fillMaxWidth()
        ) {

            myPictures?.let {
                items(it) { pic ->
                    val photoUserName = pic?.id
                    PhotoCardItem(
                        imageUrl = pic?.domainUrls?.regular,
                        navController = navController,
                        photoID = photoUserName ?: ""
                    )
                }
            }
        }
    }
}
