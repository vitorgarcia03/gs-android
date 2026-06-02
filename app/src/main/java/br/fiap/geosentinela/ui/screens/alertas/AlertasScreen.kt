package br.fiap.geosentinela.ui.screens.alertas

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.fiap.geosentinela.ui.components.GeoSentinelaBottomBar
import br.fiap.geosentinela.ui.theme.GeoSentinelaTheme

@Composable
fun RiscosScreen(
    modifier: Modifier = Modifier,
    onPainelClick: () -> Unit = {},
    onRiscosClick: () -> Unit = {},
    onMapaVivoClick: () -> Unit = {},
    onCatalogoClick: () -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            GeoSentinelaBottomBar(
                onPainelClick = onPainelClick,
                onRiscosClick = onRiscosClick,
                onMapaVivoClick = onMapaVivoClick,
                onCatalogoClick = onCatalogoClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .background(MaterialTheme.colorScheme.onBackground)
                .padding(paddingValues)
                .padding(18.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Mesa de ocorrências", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Black)
            Text(
                "Alertas agrupados por ação necessária. A tela deixa de ser uma lista simples e funciona como triagem de campo.",
                color = Color.White.copy(alpha = 0.62f),
                fontSize = 13.sp,
                lineHeight = 18.sp
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                StatusColumn("Agora", "2", Color(0xFFFF6F59), Modifier.weight(1f))
                StatusColumn("Hoje", "5", Color(0xFFD59A10), Modifier.weight(1f))
                StatusColumn("Monitorar", "9", Color(0xFF7BDFF2), Modifier.weight(1f))
            }

            OccurrenceCard(
                code = "OC-217",
                title = "Calor seco persistente",
                place = "Barreiras · BA",
                impact = "Vegetação com queda de vigor e temperatura superficial acima do histórico local.",
                action = "Acionar Defesa Civil municipal",
                color = Color(0xFFFF6F59),
                progress = 0.82f
            )
            OccurrenceCard(
                code = "OC-219",
                title = "Solo saturado em área urbana",
                place = "Pelotas · RS",
                impact = "Bairros baixos com risco de lâmina d'água nas próximas horas.",
                action = "Checar drenagem e rotas de evacuação",
                color = Color(0xFF7BDFF2),
                progress = 0.58f
            )
            OccurrenceCard(
                code = "OC-221",
                title = "Fumaça linear em borda agrícola",
                place = "Rio Verde · GO",
                impact = "Sinal térmico isolado, ainda sem confirmação por segunda fonte.",
                action = "Manter observação por 90 min",
                color = Color(0xFFD59A10),
                progress = 0.36f
            )
        }
    }
}

@Composable
private fun StatusColumn(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(26.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(value, color = color, fontSize = 26.sp, fontWeight = FontWeight.Black)
        Text(label, color = Color.Black.copy(alpha = 0.64f), fontSize = 11.sp)
    }
}

@Composable
private fun OccurrenceCard(
    code: String,
    title: String,
    place: String,
    impact: String,
    action: String,
    color: Color,
    progress: Float
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(38.dp).clip(CircleShape).background(color.copy(alpha = 0.18f)), contentAlignment = Alignment.Center) {
                    Text("!", color = color, fontSize = 20.sp, fontWeight = FontWeight.Black)
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(title, color = Color.Black, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                    Text(place, color = Color.Black.copy(alpha = 0.54f), fontSize = 12.sp)
                }
            }
            Text(code, color = color, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }

        Text(impact, color = Color.Black.copy(alpha = 0.76f), fontSize = 13.sp, lineHeight = 18.sp)

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("confiança do sinal", color = Color.Black.copy(alpha = 0.50f), fontSize = 11.sp)
                Text("${(progress * 100).toInt()}%", color = color, fontSize = 11.sp, fontWeight = FontWeight.Bold)
            }
            Box(modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(10.dp)).background(Color.White.copy(alpha = 0.10f))) {
                Box(modifier = Modifier.fillMaxWidth(progress).height(8.dp).clip(RoundedCornerShape(10.dp)).background(color))
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(12.dp)
        ) {
            Text("Próxima ação: $action", color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview
@Composable
private fun RiscosScreenPreview() {
    GeoSentinelaTheme {
        RiscosScreen()
    }
}
