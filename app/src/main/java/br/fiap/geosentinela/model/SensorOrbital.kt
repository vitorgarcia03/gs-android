package br.fiap.geosentinela.model

import java.util.Date

data class SensorOrbital(
    val id: Int,
    val nome: String,
    val regiao: BiomaRegiao,
    val ultimaLeitura: Date,
    val descricao: String
)