package com.switchthemeanimated.ui.screens


import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.switchthemeanimated.ui.Composable.ThemeSwitcher
import kotlin.math.hypot
import kotlinx.coroutines.launch
@SuppressLint("RememberReturnType")
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    onChangeTheme : () -> Unit
) {

    var radius by remember { mutableStateOf(0f) }
    val colors = MaterialTheme.colorScheme
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(if(isDarkTheme)   Color.Transparent else Color.Black)
            .drawBehind {
                drawCircle(
                    color = colors.background,
                    radius = radius,
                    center = Offset(size.width, 0f),
                )
            },
        contentAlignment = Alignment.Center
    ) {
        val animatedRadius = remember { Animatable(0f) }
        val (width, height) = with(LocalConfiguration.current) {
            with(LocalDensity.current) { screenWidthDp.dp.toPx() to screenHeightDp.dp.toPx() }
        }
        val maxRadiusPx = hypot(width, height)
        ThemeSwitcher(
            darkTheme = isDarkTheme,
            size = 30.dp,
            padding = 5.dp,
            onClick =  {
                onChangeTheme()
                coroutineScope.launch {
                    animatedRadius.animateTo(maxRadiusPx, animationSpec = tween(500)) {
                        radius = value
                    }
                    animatedRadius.snapTo(0f)
                }

            }
        )
    }
}

