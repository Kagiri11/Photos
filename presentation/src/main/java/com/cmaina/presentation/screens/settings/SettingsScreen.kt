package com.cmaina.presentation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.cmaina.presentation.R
import com.cmaina.presentation.ui.theme.FotosBlack

@Composable
fun SettingsScreen() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (titleRef, settingsOptionsColumnRef) = createRefs()
        Text(
            text = "Settings",
            style = MaterialTheme.typography.h1.copy(color = FotosBlack),
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(parent.top, margin = 20.dp)
                start.linkTo(parent.start, margin = 15.dp)
            }
        )
        Column(
            modifier = Modifier
                .constrainAs(settingsOptionsColumnRef) {
                    start.linkTo(parent.start, margin = 10.dp)
                    top.linkTo(titleRef.bottom, margin = 15.dp)
                },
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Text(text = "Display", style = MaterialTheme.typography.body1.copy(color = FotosBlack))
            Icon(painter = painterResource(id = R.drawable.ic_dark_mode), contentDescription = "", tint = FotosBlack)
        }
    }
}
