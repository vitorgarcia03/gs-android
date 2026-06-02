package br.fiap.geosentinela.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun GeoSentinelaBottomBar(
    onPainelClick: () -> Unit = {},
    onRiscosClick: () -> Unit = {},
    onMapaVivoClick: () -> Unit = {},
    onCatalogoClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(32.dp)),
        tonalElevation = 8.dp,
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.96f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavChip(R.drawable.home, "Base", onPainelClick)
            NavChip(R.drawable.bell, "Ocorrências", onRiscosClick)
            NavChip(R.drawable.monitor, "Território", onMapaVivoClick)
            NavChip(R.drawable.info, "Fontes", onCatalogoClick)
        }
    }
}

@Composable
private fun NavChip(icon: Int, label: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = onClick, modifier = Modifier.size(42.dp)) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = label,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Text(
            text = label,
            color = Color.Black.copy(alpha = 0.82f),
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview
@Composable
private fun GeoSentinelaBottomBarPreview() {
    GeoSentinelaTheme {
        GeoSentinelaBottomBar()
    }
}
