package br.fiap.geosentinela.ui.repository

import br.fiap.geosentinela.model.BiomaRegiao
import br.fiap.geosentinela.model.SensorOrbital
import java.util.Date

val sensores = listOf(
    SensorOrbital(
        id = 11,
        nome = "VerdeScope AM",
        regiao = BiomaRegiao.AM,
        ultimaLeitura = Date(),
        descricao = "Camada simulada de acompanhamento florestal. A leitura destaca fragmentação recente, alteração de biomassa e pontos que merecem validação por equipes locais."
    ),
    SensorOrbital(
        id = 12,
        nome = "Radar Água MT",
        regiao = BiomaRegiao.MT,
        ultimaLeitura = Date(),
        descricao = "Módulo de radar fictício para regiões úmidas. O painel usa essa fonte para diferenciar solo encharcado, lâmina d'água e áreas agrícolas com baixa resposta espectral."
    ),
    SensorOrbital(
        id = 13,
        nome = "Caatinga Termal",
        regiao = BiomaRegiao.BA,
        ultimaLeitura = Date(),
        descricao = "Fonte térmica simulada para calor persistente e perda de vigor vegetal. Ajuda a compor a triagem de seca, estresse hídrico e alerta de queimadas."
    ),
    SensorOrbital(
        id = 14,
        nome = "Sul Pluvial",
        regiao = BiomaRegiao.RS,
        ultimaLeitura = Date(),
        descricao = "Índice fictício de precipitação acumulada, drenagem urbana e saturação do solo. Foi pensado para sinalizar áreas com chance de alagamento e bloqueio viário."
    ),
    SensorOrbital(
        id = 15,
        nome = "Cerrado Flux",
        regiao = BiomaRegiao.GO,
        ultimaLeitura = Date(),
        descricao = "Indicador composto de vegetação seca, vento e calor. No protótipo, atua como gatilho para observação contínua antes da confirmação de um evento crítico."
    )
)

fun getAllSensores(): List<SensorOrbital> {
    return sensores
}
