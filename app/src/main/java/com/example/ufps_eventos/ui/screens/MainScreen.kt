package com.example.ufps_eventos.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ufps_eventos.model.Evento
import com.example.ufps_eventos.model.NavItem
import com.example.ufps_eventos.navigation.AppScreens
import com.example.ufps_eventos.ui.screens.pages.EventsPage
import com.example.ufps_eventos.ui.screens.pages.GroupsPage
import com.example.ufps_eventos.ui.screens.pages.CerrarSesionPage
import com.example.ufps_eventos.ui.screens.pages.events.EventDetailPage
import com.example.ufps_eventos.ui.screens.pages.groups.GroupCreatePage
import com.example.ufps_eventos.ui.screens.pages.groups.GroupDetailPage
import com.example.ufps_eventos.ui.screens.pages.groups.GroupMembersPage
import com.example.ufps_eventos.ui.screens.pages.groups.GroupSubscribePage
import com.example.ufps_eventos.ui.screens.pages.groups.admin.GroupAdminDetailPage
import com.example.ufps_eventos.ui.screens.pages.groups.admin.GroupEditPage
import com.example.ufps_eventos.ui.screens.pages.groups.admin.GroupMembersAdminPage
import com.google.gson.Gson


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, modifier: Modifier = Modifier) {

    val internalNavController = rememberNavController()

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val routesWithBottomBar = listOf("event_detail_page")

    val currentRoute = internalNavController.currentBackStackEntryAsState().value?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute !in routesWithBottomBar) {
                NavigationBottomBar(navController = internalNavController, loginNavController = navController)
                println("Entro al if del bottomBar")
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = internalNavController,
            startDestination = "grupos",
            Modifier.padding(innerPadding)
        ) {
            composable("grupos") { GroupsPage(internalNavController) }
            composable("eventos") { EventsPage(internalNavController) }
            composable("cerrar_sesion") { CerrarSesionPage(internalNavController) }
            composable("crear_grupo"){ GroupCreatePage(internalNavController) }
            composable("editar_grupo"){ GroupEditPage(internalNavController) }
            composable("editar_detalles_grupo"){ GroupAdminDetailPage(internalNavController) }
            composable("detalle_grupo"){ GroupDetailPage(internalNavController) }
            composable("suscribirse_grupo"){ GroupSubscribePage(internalNavController) }
            composable("miembros_grupo"){ GroupMembersPage(internalNavController) }
            composable("miembros_grupo_admin"){ GroupMembersAdminPage(internalNavController) }
            composable(
                route = "event_detail_page/{eventoJson}",
                arguments = listOf(navArgument("eventoJson") { type = NavType.StringType })
            ) { backStackEntry ->
                val eventoJson = backStackEntry.arguments?.getString("eventoJson")
                val evento = Gson().fromJson(eventoJson, Evento::class.java)
                EventDetailPage(evento, internalNavController)
            }
        }
    }
}


@Composable
fun NavigationBottomBar(navController: NavController, loginNavController: NavController) {

    val navItemList = listOf(
        NavItem("Grupos", Icons.Default.Person,0, "grupos"),
        NavItem("Eventos", Icons.Default.Menu,0, "eventos"),
        NavItem("Cerrar Sesión", Icons.Default.ExitToApp,0, "exit"),
    )

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar {
        navItemList.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,  // Marcar como seleccionado si la ruta actual coincide
                onClick = {
                    if (navItem.route == "exit") {
                        //loginNavController.navigate(AppScreens.LoginScreen.route)
                        loginNavController.popBackStack()
                    } else {
                        navController.navigate(navItem.route) {
                            // Evitar apilar pantallas si la ruta es la misma
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },
                label = {
                    Text(text = navItem.label)
                }
            )
        }
    }
}

/*@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex : Int) {
    when(selectedIndex){
        0-> GruposPage()
        1-> EventosPage()
        2-> CerrarSesionPage()
    }
}*/