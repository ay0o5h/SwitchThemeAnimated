package com.switchthemeanimated

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.switchthemeanimated.ui.screens.MainScreen
import com.switchthemeanimated.ui.theme.SwitchThemeAnimatedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           var  isDarkTheme: Boolean = isSystemInDarkTheme()
            var darkTheme by remember { mutableStateOf(isDarkTheme) }
            SwitchThemeAnimatedTheme(darkTheme=darkTheme) {
                MainScreen(
                    isDarkTheme = darkTheme,
                    onChangeTheme = { darkTheme = !darkTheme }
                )
            }
        }
    }
}
