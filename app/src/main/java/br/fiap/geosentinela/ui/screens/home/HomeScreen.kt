package br.fiap.geosentinela.ui.screens.home

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
fun PainelScreen(
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
                .padding(horizontal = 18.dp, vertical = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "Central de campo",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        text = "Resumo operacional · 01/06/2026",
                        color = Color.White.copy(alpha = 0.62f),
                        fontSize = 13.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(18.dp))
                        .background(Color(0xFF223126))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text("ONLINE", color = Color(0xFF8FD694), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }

            HeroSituationCard()

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
                MetricTile("Cobertura", "84%", "5 biomas lidos", Color(0xFFD59A10), Modifier.weight(1f))
                MetricTile("Pendências", "03", "vistoria em fila", Color(0xFFFF6F59), Modifier.weight(1f))
                MetricTile("Chuva", "41 mm", "média 24h", Color(0xFF7BDFF2), Modifier.weight(1f))
            }

            Text(
                text = "Fila de decisão",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            DecisionRow("Enviar equipe para trecho crítico", "Matopiba", "prioridade 1", Color(0xFFFF6F59))
            DecisionRow("Validar umidade em área agrícola", "Cerrado GO", "prioridade 2", Color(0xFFD59A10))
            DecisionRow("Arquivar leitura sem anomalia", "Pampa RS", "rotina", Color(0xFF8FD694))
        }
    }
}

@Composable
private fun HeroSituationCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(22.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("Nível nacional", color = Color.Black.copy(alpha = 0.58f), fontSize = 13.sp)
                    Text("Atenção moderada", color = MaterialTheme.colorScheme.primary, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
                }
                RingBadge("68")
            }
            MiniMap()
            Text(
                text = "O maior desvio está concentrado em calor seco no Nordeste e saturação de solo no litoral sul. O restante da malha segue em observação normal.",
                color = Color.Black.copy(alpha = 0.78f),
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
private fun RingBadge(value: String) {
    Box(
        modifier = Modifier
            .size(66.dp)
            .clip(CircleShape)
            .background(Color(0xFF332A18)),
        contentAlignment = Alignment.Center
    ) {
        Text(value, color = MaterialTheme.colorScheme.primary, fontSize = 22.sp, fontWeight = FontWeight.Black)
    }
}

@Composable
private fun MiniMap() {
    Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
        repeat(4) { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                repeat(6) { col ->
                    val color = when ((row * 6 + col) % 5) {
                        0 -> Color(0xFF8FD694)
                        1 -> Color(0xFFD59A10)
                        2 -> Color(0xFFFF6F59)
                        else -> Color(0xFF2A2420)
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(if ((row + col) % 2 == 0) 22.dp else 14.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(color.copy(alpha = 0.86f))
                    )
                }
            }
        }
    }
}

@Composable
private fun MetricTile(title: String, value: String, note: String, accent: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(22.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(modifier = Modifier.size(8.dp).clip(CircleShape).background(accent))
        Text(title, color = Color.Black.copy(alpha = 0.62f), fontSize = 11.sp)
        Text(value, color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(note, color = Color.Black.copy(alpha = 0.50f), fontSize = 10.sp)
    }
}

@Composable
private fun DecisionRow(title: String, region: String, tag: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(12.dp).clip(CircleShape).background(color))
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(region, color = Color.Black.copy(alpha = 0.56f), fontSize = 12.sp)
        }
        Text(tag.uppercase(), color = color, fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
private fun PainelScreenPreview() {
    GeoSentinelaTheme {
        PainelScreen()
    }
}
