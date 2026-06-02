package br.fiap.geosentinela

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.fiap.geosentinela.navigation.AppNavigation
import br.fiap.geosentinela.ui.theme.GeoSentinelaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeoSentinelaTheme {
                AppNavigation()
            }
        }
    }
}