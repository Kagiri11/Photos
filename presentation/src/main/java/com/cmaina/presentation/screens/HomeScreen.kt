package com.cmaina.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.compose.collectAsLazyPagingItems
import com.cmaina.presentation.components.photoscards.PhotoCardItem
import com.cmaina.presentation.components.photostext.FotosTitleText
import com.cmaina.presentation.materials.LazyStaggeredGrid
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.ui.theme.FotosWhite
import com.cmaina.presentation.viewmodels.HomeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = getViewModel()) {
    val myPictures = viewModel.pics.observeAsState().value?.collectAsLazyPagingItems()

    ConstraintLayout(modifier = Modifier.fillMaxSize().background(FotosWhite)) {
        val (title, fotosGrid) = createRefs()
        FotosTitleText(
            text = "Explore",
            textColor = FotosBlack,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, margin = 30.dp)
                start.linkTo(parent.start, margin = 10.dp)
            }
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(2.dp),
            modifier = Modifier
                .constrainAs(fotosGrid) {
                    top.linkTo(title.bottom, margin = 20.dp)
                }
        ) {

            myPictures?.let {
                items(it) { pic ->
                    PhotoCardItem(imageUrl = pic?.domainUrls?.regular)
                }
            }
        }

        /*LazyStaggeredGrid(
            columnCount = 2,
            contentPadding = PaddingValues(2.dp),
        ) {
            item {

            }
        }*/

        /*StaggeredVerticalGrid(
            maxColumnWidth = 200.dp,
            modifier = Modifier
                .constrainAs(fotosGrid) {
                    top.linkTo(title.bottom, margin = 20.dp)
                }
                .padding(5.dp)
        ) {
            myPictures.forEach {
                PhotoCardItem(imageUrl = it.domainUrls?.regular)
            }
        }*/
    }
}
