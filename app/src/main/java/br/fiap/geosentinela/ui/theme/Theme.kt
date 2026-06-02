package br.fiap.geosentinela.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    background = CarbonNight,
    surface = InkPanel,
    primary = SandGlow,
    secondary = MossSignal,
    tertiary = IceBlue,
    error = CoralAlert,
    onPrimary = CarbonNight,
    onBackground = CarbonNight,
    onSurface = SandGlow,
    surfaceVariant = ClayPanel,
)

private val LightColorScheme = lightColorScheme(
    background = ColorTokens.LightBase,
    surface = ColorTokens.LightCard,
    primary = ColorTokens.LightAccent,
    secondary = ColorTokens.LightMoss,
    surfaceVariant = ColorTokens.LightWarm,
)

private object ColorTokens {
    val LightBase = androidx.compose.ui.graphics.Color(0xFFF5F0E8)
    val LightCard = androidx.compose.ui.graphics.Color(0xFFFFFAF1)
    val LightAccent = androidx.compose.ui.graphics.Color(0xFF7A4E00)
    val LightMoss = androidx.compose.ui.graphics.Color(0xFF2B6D42)
    val LightWarm = androidx.compose.ui.graphics.Color(0xFFE7D3B6)
}

@Composable
fun GeoSentinelaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
