package br.fiap.geosentinela.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.fiap.geosentinela.ui.screens.alertas.RiscosScreen
import br.fiap.geosentinela.ui.screens.home.PainelScreen
import br.fiap.geosentinela.ui.screens.infoSensor.InfoSensorScreen
import br.fiap.geosentinela.ui.screens.intro.IntroScreen
import br.fiap.geosentinela.ui.screens.monitorar.MapaVivoScreen
import br.fiap.geosentinela.ui.screens.splash.SplashScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashRoute
    ) {

        composable<SplashRoute> {
            SplashScreen(
                onNavigateToIntro = {
                    navController.navigate(IntroRoute) {
                        popUpTo<SplashRoute> { inclusive = true }
                    }
                }
            )
        }

        composable<IntroRoute> {
            IntroScreen(
                onNavigateToPainel = {
                    navController.navigate(PainelRoute) {
                        popUpTo<IntroRoute> { inclusive = true }
                    }
                }
            )
        }

        composable<PainelRoute> {
            PainelScreen(
                onPainelClick = { navController.navigate(PainelRoute) },
                onRiscosClick = { navController.navigate(RiscosRoute) },
                onMapaVivoClick = { navController.navigate(MapaVivoRoute) },
                onCatalogoClick = { navController.navigate(InfoSensorRoute) }
            )
        }

        composable<RiscosRoute> {
            RiscosScreen(
                onPainelClick = { navController.navigate(PainelRoute) },
                onRiscosClick = { navController.navigate(RiscosRoute) },
                onMapaVivoClick = { navController.navigate(MapaVivoRoute) },
                onCatalogoClick = { navController.navigate(InfoSensorRoute) }
            )
        }

        composable<MapaVivoRoute> {
            MapaVivoScreen(
                onPainelClick = { navController.navigate(PainelRoute) },
                onRiscosClick = { navController.navigate(RiscosRoute) },
                onMapaVivoClick = { navController.navigate(MapaVivoRoute) },
                onCatalogoClick = { navController.navigate(InfoSensorRoute) }
            )
        }

        composable<InfoSensorRoute> {
            InfoSensorScreen(
                onPainelClick = { navController.navigate(PainelRoute) },
                onRiscosClick = { navController.navigate(RiscosRoute) },
                onMapaVivoClick = { navController.navigate(MapaVivoRoute) },
                onCatalogoClick = { navController.navigate(InfoSensorRoute) }
            )
        }
    }
}