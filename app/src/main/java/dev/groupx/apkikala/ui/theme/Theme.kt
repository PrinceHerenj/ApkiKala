package dev.groupx.apkikala.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable


private val DarkColorPalette = darkColorScheme(
    primary = Rose80,
    onPrimary = Rose20,
    primaryContainer = Rose70,
    onPrimaryContainer = Rose30,
    secondary = Nut3,
    onSecondary = Nut0,
    secondaryContainer = Nut2,
    onSecondaryContainer = Nut0,
    tertiary = Red20,
    onTertiary = Red10,
    tertiaryContainer = Red20,
    onTertiaryContainer = Red10,
    error = Red90,
    onError = Red10,
    errorContainer = Rose40,
    onErrorContainer = Rose10

)

private val LightColorPalette = lightColorScheme(
    primary = Earth50,
    secondary = Nut3

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ApkiKalaTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}