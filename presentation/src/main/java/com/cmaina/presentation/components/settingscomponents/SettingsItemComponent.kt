package com.cmaina.presentation.components.settingscomponents

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmaina.presentation.components.photostext.FotosText
import com.cmaina.presentation.ui.theme.FotosGreyShadeThreeLightTheme

@Composable
fun Setting(
    modifier: Modifier = Modifier.semantics { contentDescription = "setting column" },
    settingName: String,
    settingAttribute: String,
    attributeValue: String,
    @DrawableRes settingIcon: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 10.dp)
            .clickable {
                onClick()
            }
            .semantics {
                contentDescription = "setting column"
            }
    ) {
        FotosText(
            text = settingName,
            textColor = FotosGreyShadeThreeLightTheme,
            fontSize = 11
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = settingIcon),
                contentDescription = "",
                tint = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                FotosText(
                    text = settingAttribute,
                    textColor = MaterialTheme.colors.onPrimary
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
