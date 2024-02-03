package com.cmaina.fotos.shared.presentation.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

expect object PainterResource {
    @Composable
    fun DownloadArrow(): Painter

    @Composable
    fun BaselineChevron(): Painter

    @Composable
    fun BaselineKey(): Painter

    @Composable
    fun BaselineMessage(): Painter

    @Composable
    fun Bookmark(): Painter

    @Composable
    fun Camera(): Painter

    @Composable
    fun DarkMode(): Painter

    @Composable
    fun Favourite(): Painter

    @Composable
    fun Home(): Painter

    @Composable
    fun LauncherBackground(): Painter

    @Composable
    fun LauncherForeground(): Painter

    @Composable
    fun Likes(): Painter

    @Composable
    fun MoreVert(): Painter

    @Composable
    fun Search(): Painter

    @Composable
    fun Settings(): Painter

    @Composable
    fun WhiteIcon(): Painter
}