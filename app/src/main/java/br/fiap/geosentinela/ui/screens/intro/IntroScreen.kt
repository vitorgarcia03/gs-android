package br.fiap.geosentinela.ui.screens.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.fiap.geosentinela.R
import br.fiap.geosentinela.ui.theme.GeoSentinelaTheme

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    onNavigateToPainel: () -> Unit = {}
) {
    var currentStep by remember { mutableStateOf(0) }

    val title = when (currentStep) {
        0 -> "Observação que vira ação"
        1 -> "Da imagem ao protocolo"
        else -> "Operação pronta"
    }
    val body = when (currentStep) {
        0 -> "O aplicativo organiza leituras ambientais simuladas em uma central de decisão, com prioridade para risco territorial."
        1 -> "Cada área passa por coleta, leitura de anomalia, classificação de risco e recomendação objetiva para campo."
        else -> "Use a barra inferior para alternar entre base operacional, ocorrências, território e biblioteca técnica."
    }

    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.onBackground)
                .padding(paddingValues)
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(36.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(26.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painterResource(R.drawable.earth),
                        contentDescription = "Símbolo ambiental",
                        modifier = Modifier.size(158.dp)
                    )
                    Spacer(Modifier.height(20.dp))
                    StepDots(currentStep)
                    Spacer(Modifier.height(22.dp))
                    Text(
                        text = title,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center,
                        lineHeight = 34.sp
                    )
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = body,
                        color = Color.Black,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center,
                        lineHeight = 21.sp
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                FeatureLine("01", "Triagem visual de eventos")
                FeatureLine("02", "Mapa sintético por bioma")
                FeatureLine("03", "Catálogo de fontes ambientais")
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                if (currentStep > 0) {
                    OutlinedButton(onClick = { currentStep-- }) { Text("Anterior") }
                } else {
                    Spacer(modifier = Modifier.width(1.dp))
                }
                Button(onClick = { if (currentStep < 2) currentStep++ else onNavigateToPainel() }) {
                    Text(if (currentStep < 2) "Próximo" else "Entrar")
                }
            }
        }
    }
}

@Composable
private fun StepDots(currentStep: Int) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(3) { index ->
            Box(
                modifier = Modifier
                    .size(if (index == currentStep) 13.dp else 9.dp)
                    .clip(CircleShape)
                    .background(if (index == currentStep) MaterialTheme.colorScheme.primary else Color.Black.copy(alpha = 0.22f))
            )
        }
    }
}

@Composable
private fun FeatureLine(number: String, label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(number, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Black)
        Spacer(Modifier.width(12.dp))
        Text(label, color = Color.Black, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Preview
@Composable
private fun IntroScreenPreview() {
    GeoSentinelaTheme {
        IntroScreen()
    }
}
