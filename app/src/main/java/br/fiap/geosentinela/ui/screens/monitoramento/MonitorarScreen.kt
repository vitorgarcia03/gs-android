package br.fiap.geosentinela.ui.screens.monitorar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.fiap.geosentinela.model.BiomaRegiao
import br.fiap.geosentinela.ui.components.BiomaCard
import br.fiap.geosentinela.ui.components.GeoSentinelaBottomBar
import br.fiap.geosentinela.ui.components.labelForRegion
import br.fiap.geosentinela.ui.repository.getAllSensores
import br.fiap.geosentinela.ui.theme.GeoSentinelaTheme
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun MapaVivoScreen(
    modifier: Modifier = Modifier,
    onPainelClick: () -> Unit = {},
    onRiscosClick: () -> Unit = {},
    onMapaVivoClick: () -> Unit = {},
    onCatalogoClick: () -> Unit = {}
) {
    var regiaoSelecionada by remember { mutableStateOf(BiomaRegiao.AM) }
    val sensoresState = remember { getAllSensores() }
    val sensor = sensoresState.firstOrNull { it.regiao == regiaoSelecionada }
    val formatter = SimpleDateFormat("HH:mm · dd/MM", Locale("pt", "BR"))

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
                .padding(vertical = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(modifier = Modifier.padding(horizontal = 18.dp)) {
                Text(
                    "Território",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    "Escolha uma área e veja o estado operacional da leitura.",
                    color = Color.White.copy(alpha = 0.60f),
                    fontSize = 13.sp
                )
            }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 18.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(BiomaRegiao.entries) { regiao ->
                    BiomaCard(regiao = regiao, onClick = { regiaoSelecionada = it })
                }
            }

            TerritoryMap(regiaoSelecionada)

            if (sensor == null) {
                EmptyRegion()
            } else {
                Column(
                    modifier = Modifier.padding(horizontal = 18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        ReadingTile("Fonte", sensor.nome, Modifier.weight(1f))
                        ReadingTile(
                            "Última coleta",
                            formatter.format(sensor.ultimaLeitura),
                            Modifier.weight(1f)
                        )
                    }
                    SensorNarrative(labelForRegion(sensor.regiao), sensor.descricao)
                    TransectTimeline()
                }
            }
        }
    }
}

@Composable
private fun TerritoryMap(regiao: BiomaRegiao) {
    Box(
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth()
            .height(230.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(18.dp)
    ) {
        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
            Text(
                "Mapa sintético · ${labelForRegion(regiao)}",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(5) { row ->
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        repeat(7) { col ->
                            val hot = (row + col + regiao.ordinal) % 6 == 0
                            val watch = (row * col + regiao.ordinal) % 5 == 0
                            val color = when {
                                hot -> Color(0xFFFF6F59)
                                watch -> Color(0xFFD59A10)
                                else -> Color(0xFF34343D)
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(if (hot) 24.dp else 16.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(color)
                            )
                        }
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                LegendDot("normal", Color(0xFF34343D))
                LegendDot("atenção", Color(0xFFD59A10))
                LegendDot("crítico", Color(0xFFFF6F59))
            }
        }
    }
}

@Composable
private fun LegendDot(label: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .size(9.dp)
            .clip(CircleShape)
            .background(color))
        Spacer(Modifier.width(6.dp))
        Text(label, color = Color.Black.copy(alpha = 0.62f), fontSize = 11.sp)
    }
}

@Composable
private fun ReadingTile(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(label, color = Color.Black.copy(alpha = 0.55f), fontSize = 11.sp)
        Text(value, color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun SensorNarrative(region: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            "Leitura interpretada",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Área selecionada: $region",
            color = Color.Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            description,
            color = Color.Black.copy(alpha = 0.72f),
            fontSize = 13.sp,
            lineHeight = 18.sp
        )
    }
}

@Composable
private fun TransectTimeline() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Linha de processamento", color = Color.Black, fontWeight = FontWeight.Bold)
        TimelineItem("Coleta bruta", "recebida", Color(0xFF8FD694))
        TimelineItem("Correção atmosférica", "concluída", Color(0xFF8FD694))
        TimelineItem("Classificação de risco", "em revisão", Color(0xFFD59A10))
    }
}

@Composable
private fun TimelineItem(title: String, status: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .size(12.dp)
            .clip(CircleShape)
            .background(color))
        Spacer(Modifier.width(10.dp))
        Text(title, color = Color.Black, modifier = Modifier.weight(1f), fontSize = 13.sp)
        Text(status, color = color, fontSize = 11.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun EmptyRegion() {
    Box(
        modifier = Modifier
            .padding(horizontal = 18.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(20.dp)
    ) {
        Text(
            "Nenhuma fonte localizada para esse bioma.",
            color = Color.Black.copy(alpha = 0.65f),
            fontSize = 13.sp
        )
    }
}

@Preview
@Composable
private fun MapaVivoScreenPreview() {
    GeoSentinelaTheme {
        MapaVivoScreen()
    }
}
