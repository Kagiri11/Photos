package com.cmaina.presentation.components.userscreencomponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cmaina.presentation.components.photostext.FotosText
import com.cmaina.presentation.components.photostext.FotosTitleText
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.ui.theme.FotosGreyShadeThreeLightTheme
import com.cmaina.presentation.ui.theme.FotosGreyShadeTwoLightTheme
import com.cmaina.presentation.ui.theme.FotosWhite

@Composable
fun FollowAndMessageButtons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        UserButton(
            text = "Follow",
            buttonColor = FotosBlack,
            textColor = FotosWhite,
            modifier = Modifier.weight(1f)
        )
        UserButton(
            text = "Message",
            buttonColor = FotosWhite,
            textColor = FotosBlack,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun FollowingSection(modifier: Modifier, photos: Int, followers: Int, following: Int) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DetailsColumn(text = "Photos", number = photos)
        Spacer(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(FotosGreyShadeTwoLightTheme.copy(alpha = 0.2f))
        )
        DetailsColumn(text = "Followers", number = followers)
        Spacer(
            modifier = Modifier
                .width(1.dp)
                .fillMaxHeight()
                .background(FotosGreyShadeTwoLightTheme.copy(alpha = 0.2f))
        )
        DetailsColumn(text = "Following", number = following)
    }
}

@Composable
fun DetailsColumn(text: String, number: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(100.dp)) {
        FotosText(text = text, textColor = FotosGreyShadeThreeLightTheme)
        FotosTitleText(text = number.toString(), textColor = FotosBlack)
    }
}

@Composable
fun UserButton(text: String, buttonColor: Color, textColor: Color, modifier: Modifier) {
    Button(
        onClick = {},
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .height(55.dp),
        border = BorderStroke(width = 1.dp, color = FotosBlack),
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor),
        elevation = ButtonDefaults.elevation(0.dp)

    ) {
        FotosText(text = text, textColor = textColor)
    }
}
