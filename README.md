# gs-android
Projeto GS Android Kotlin Developer - GeoSentinela

# GeoSentinela

Aplicativo mobile desenvolvido em **Android com Kotlin e Jetpack Compose** para simular uma plataforma de monitoramento ambiental, análise territorial e apoio à tomada de decisão em campo.

---

## 1. Objetivo do projeto

O **GeoSentinela** tem como objetivo representar uma central digital de acompanhamento ambiental.  
A aplicação simula a leitura de sensores, alertas territoriais, dados climáticos, risco ambiental e informações de apoio técnico para equipes de campo.

A ideia principal é permitir que o usuário visualize rapidamente:

- áreas com possível risco ambiental;
- alertas operacionais;
- indicadores de sensores simulados;
- situação de biomas e territórios monitorados;
- recomendações de ação;
- biblioteca técnica com dados e referências fictícias.

---

## 2. Tecnologias utilizadas

O projeto utiliza:

- **Kotlin** como linguagem principal;
- **Jetpack Compose** para construção das interfaces;
- **Navigation Compose** para navegação entre telas;
- **Gradle** para gerenciamento do build;
- recursos visuais em `res/drawable`;
- tema customizado em arquivos de UI/theme.

---

## 3. Identidade visual

A interface foi desenvolvida com uma identidade mais técnica e ambiental.

A paleta visual usa tons de:

- carbono;
- areia;
- musgo;
- coral;
- azul-gelo;
- verde ambiental;
- tons neutros escuros para contraste.

O objetivo visual é transmitir uma central de monitoramento, com aparência de painel operacional e foco em leitura rápida de informações.

---

## 4. Estrutura geral do aplicativo

O aplicativo é dividido em telas principais acessadas por uma barra inferior flutuante.

---

## 5. Telas do projeto

### 5.1 Splash Screen

Tela inicial de carregamento do aplicativo.

Função:

- apresentar a marca **GeoSentinela**;
- introduzir a identidade visual do projeto;
- criar uma entrada mais profissional antes da navegação principal.

---

### 5.2 Tela de Introdução

Tela explicativa exibida antes da área principal do aplicativo.

Função:

- explicar a proposta do app;
- apresentar a ideia de monitoramento territorial;
- direcionar o usuário para a central principal.

---

### 5.3 Central de Campo

Tela principal do aplicativo.

Função:

- exibir um resumo geral da operação;
- apresentar indicadores ambientais simulados;
- mostrar cards de risco e leitura rápida;
- servir como painel inicial para tomada de decisão.

Elementos principais:

- cabeçalho com status da operação;
- cards de métricas;
- mapa sintético ou área visual de monitoramento;
- fila de decisão;
- resumo de sensores ativos.

---

### 5.4 Mesa de Ocorrências

Tela focada na triagem dos alertas ambientais.

Função:

- listar ocorrências simuladas;
- indicar nível de prioridade;
- mostrar confiança do sinal;
- sugerir próxima ação.

Elementos principais:

- lista de ocorrências;
- chips de prioridade;
- descrição resumida do evento;
- indicação de fonte do alerta;
- recomendação operacional.

---

### 5.5 Território

Tela equivalente ao mapa vivo do projeto, mas com estrutura modificada.

Função:

- representar regiões monitoradas;
- mostrar blocos por bioma ou zona;
- indicar intensidade de risco;
- exibir legenda e processamento dos dados.

Elementos principais:

- mapa visual em blocos;
- legenda de risco;
- cards por região;
- linha de processamento;
- indicadores de cobertura territorial.

---

### 5.6 Biblioteca Técnica

Tela de apoio com informações e dados fictícios.

Função:

- organizar referências simuladas;
- apresentar categorias ambientais;
- exibir fontes e dados técnicos;
- complementar a análise das demais telas.

Elementos principais:

- campo de busca visual;
- chips de categoria;
- cards técnicos;
- contadores de registros;
- dados por fonte, bioma ou tipo de evento.

---

## 6. Fluxo de navegação

A navegação principal ocorre por meio de uma barra inferior em formato de pílula/flutuante.

Cada tela foi pensada para cumprir uma função específica:

- **Central de Campo:** visão geral e resumo da operação;
- **Mesa de Ocorrências:** tratamento de alertas;
- **Território:** visualização territorial;
- **Biblioteca Técnica:** consulta de informações simuladas.

---

## 7. Dados simulados

Os dados exibidos no app são fictícios e servem apenas para demonstração acadêmica.

Exemplos de dados simulados:

- percentual de confiança do sinal;
- risco ambiental;
- status de sensores;
- leitura de umidade;
- focos térmicos;
- regiões monitoradas;
- alertas por prioridade;
- categorias de bioma.

Nenhum dado real de satélite, sensor ou órgão ambiental é consumido pelo aplicativo.

---

## 8. Resumo do fluxo com telas

Descrição rápida:

```text
Splash
  Apresenta o nome e identidade do app.

Introdução
  Explica a proposta de monitoramento ambiental.

Central de Campo
  Mostra resumo, métricas e situação geral.

Mesa de Ocorrências
  Lista alertas, prioridades e ações recomendadas.

Território
  Representa áreas monitoradas e níveis de risco.

Biblioteca Técnica
  Exibe dados de apoio, categorias e referências simuladas.
```
