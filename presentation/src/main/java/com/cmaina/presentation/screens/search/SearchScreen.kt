package com.cmaina.presentation.screens.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.cmaina.presentation.components.photoscards.PhotoCardItem
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchScreen(searchViewModel: SearchViewModel = getViewModel(), navController: NavController) {
    val searchedPhotos = searchViewModel.searchedPhotos.collectAsState().value
    ConstraintLayout(Modifier.fillMaxSize()) {
        val searchText = remember { mutableStateOf("") }

        val (searchBar, fotosGrid) = createRefs()
        TextField(
            value = searchText.value,
            onValueChange = {
                when {
                    it.isEmpty() -> searchViewModel.returnNullPhotos()
                    else -> searchViewModel.searchPhotos(it)
                }
                searchText.value = it
            },
            modifier = Modifier.constrainAs(searchBar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(1.dp),
            modifier = Modifier
                .constrainAs(fotosGrid) {
                    top.linkTo(searchBar.bottom, margin = 20.dp)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
                .fillMaxWidth()
        ) {

            searchedPhotos?.let {
                items(it) { pic ->
                    val photoUserName = pic.id
                    PhotoCardItem(
                        imageUrl = pic.domainUrls?.regular,
                        navController = navController,
                        photoID = photoUserName ?: ""
                    )
                }
            }
        }
    }
}
