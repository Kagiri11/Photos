package com.cmaina.presentation.components.settingscomponents

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmaina.presentation.ui.theme.FotosBlack
import com.cmaina.presentation.ui.theme.FotosGreyShadeThreeLightTheme

@Composable
fun Setting(
    settingName: String,
    settingAttribute: String,
    attributeValue: String,
    @DrawableRes settingIcon: Int,
    onClick: () -> Unit
) {
    Column {
        Text(
            text = settingName,
            style = MaterialTheme.typography.body1.copy(
                color = FotosGreyShadeThreeLightTheme,
                fontSize = 11.sp
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable {
                    onClick()
                }
        ) {
            Icon(
                painter = painterResource(id = settingIcon),
                contentDescription = "",
                tint = FotosBlack
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(
                    text = settingAttribute,
                    style = MaterialTheme.typography.body1.copy(color = FotosBlack)
                )
                Text(
                    text = attributeValue,
                    style = MaterialTheme.typography.body1.copy(
                        color = FotosGreyShadeThreeLightTheme,
                        fontSize = 11.sp
                    )
                )
            }
        }
    }
}
