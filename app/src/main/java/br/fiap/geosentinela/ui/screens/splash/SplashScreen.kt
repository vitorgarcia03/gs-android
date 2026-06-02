package br.fiap.geosentinela.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.fiap.geosentinela.R
import br.fiap.geosentinela.ui.theme.GeoSentinelaTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToIntro: () -> Unit = {}
) {
    LaunchedEffect(Unit) {
        delay(2200)
        onNavigateToIntro()
    }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.onBackground),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier.fillMaxWidth().padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(132.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painterResource(R.drawable.satelite),
                        contentDescription = "Marca GeoSentinela",
                        modifier = Modifier.size(84.dp)
                    )
                }
                Spacer(modifier = Modifier.height(28.dp))
                Text(
                    text = "GEOSENTINELA",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "central ambiental de campo",
                    color = Color.White.copy(alpha = 0.58f),
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    GeoSentinelaTheme {
        SplashScreen()
    }
}
