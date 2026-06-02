package br.fiap.geosentinela.ui.screens.infoSensor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import br.fiap.geosentinela.model.SensorOrbital
import br.fiap.geosentinela.ui.components.GeoSentinelaBottomBar
import br.fiap.geosentinela.ui.components.labelForRegion
import br.fiap.geosentinela.ui.repository.getAllSensores
import br.fiap.geosentinela.ui.theme.GeoSentinelaTheme

@Composable
fun InfoSensorScreen(
    modifier: Modifier = Modifier,
    onPainelClick: () -> Unit = {},
    onRiscosClick: () -> Unit = {},
    onMapaVivoClick: () -> Unit = {},
    onCatalogoClick: () -> Unit = {}
) {
    var searchTextState by remember { mutableStateOf("") }
    val sensoresState = remember { getAllSensores() }

    val listaFiltrada = sensoresState.filter {
        it.nome.contains(searchTextState, ignoreCase = true) ||
            labelForRegion(it.regiao).contains(searchTextState, ignoreCase = true)
    }

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
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text("Biblioteca técnica", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Black)
                Text("Fontes simuladas, uso no protótipo e área de aplicação.", color = Color.White.copy(alpha = 0.60f), fontSize = 13.sp)
            }

            OutlinedTextField(
                value = searchTextState,
                onValueChange = { searchTextState = it },
                label = { Text("Filtrar por fonte ou bioma") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()) {
                CatalogCounter("Fontes", sensoresState.size.toString(), Color(0xFFD59A10), Modifier.weight(1f))
                CatalogCounter("Biomas", "5", Color(0xFF8FD694), Modifier.weight(1f))
                CatalogCounter("Modelos", "3", Color(0xFF7BDFF2), Modifier.weight(1f))
            }

            if (listaFiltrada.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Nenhuma fonte localizada", color = Color.Black.copy(alpha = 0.65f), fontSize = 16.sp)
                }
            } else {
                LazyColumn(
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(listaFiltrada) { sensor ->
                        SensorInfoCard(sensor = sensor)
                    }
                }
            }
        }
    }
}

@Composable
private fun CatalogCounter(label: String, value: String, color: Color, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(22.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(value, color = color, fontSize = 22.sp, fontWeight = FontWeight.Black)
        Text(label, color = Color.Black.copy(alpha = 0.62f), fontSize = 11.sp)
    }
}

@Composable
fun SensorInfoCard(sensor: SensorOrbital) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(26.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF332A18)),
                contentAlignment = Alignment.Center
            ) {
                Text(sensor.nome.take(2).uppercase(), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Black, fontSize = 13.sp)
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(sensor.nome, color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(labelForRegion(sensor.regiao), color = Color.Black.copy(alpha = 0.56f), fontSize = 12.sp)
            }
            Text("ID ${sensor.id}", color = MaterialTheme.colorScheme.primary, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TechTag("radar")
            TechTag("vegetação")
            TechTag("risco")
        }

        Text(sensor.descricao, color = Color.Black.copy(alpha = 0.72f), fontSize = 13.sp, lineHeight = 18.sp)
    }
}

@Composable
private fun TechTag(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(text, color = Color.Black.copy(alpha = 0.70f), fontSize = 10.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Preview
@Composable
private fun InfoSensorScreenPreview() {
    GeoSentinelaTheme {
        InfoSensorScreen()
    }
}
