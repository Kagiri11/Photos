package com.cmaina.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.cmaina.presentation.viewmodels.HomeViewModel
import com.cmaina.presentation.components.photoscards.PhotoCardItem
import com.cmaina.presentation.components.photostext.FotosTitleText
import com.cmaina.presentation.materials.StaggeredVerticalGrid
import com.cmaina.presentation.ui.theme.FotosBlack
import org.koin.androidx.compose.getViewModel

@Composable
fun Greeting(viewModel: HomeViewModel = getViewModel()) {
    val myPictures = viewModel.pics.collectAsState().value
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (title, fotosGrid) = createRefs()
        FotosTitleText(
            text = "Explore",
            textColor = FotosBlack,
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top, margin = 30.dp)
                start.linkTo(parent.start, margin = 10.dp)
            }
        )
        /*LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(2.dp),
            modifier = Modifier
                .constrainAs(fotosGrid) {
                    top.linkTo(title.bottom, margin = 20.dp)
                }
        ) {
            items(myPictures) { pic ->
                PhotoCardItem(imageUrl = pic.domainUrls?.regular)
            }
        }*/

        StaggeredVerticalGrid(
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
        }
    }
}