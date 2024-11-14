package com.example.ufps_eventos.ui.screens.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ufps_eventos.R
import com.example.ufps_eventos.model.Grupo

@Composable
fun GroupsPage(navController: NavController, modifier: Modifier = Modifier) {
    GroupsPageView(navController = navController)
}

@Composable
fun GroupsPageView(navController: NavController, modifier: Modifier = Modifier) {
    var selectedCategory by remember { mutableStateOf("Categorías") }
    var showCategoryDropdown by remember { mutableStateOf(false) }
    val grupos = generarGrupos(15)  // Cambia la cantidad de grupos según necesites
    var currentPage by remember { mutableStateOf(1) }
    val itemsPerPage = 8
    val totalPages = (grupos.size + itemsPerPage - 1) / itemsPerPage
    val paginatedGrupos = grupos.drop((currentPage - 1) * itemsPerPage).take(itemsPerPage)

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // Barra superior
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Grupos", style = MaterialTheme.typography.titleLarge)

            Row {
                // Selector de Categorías
                Box {
                    TextButton(onClick = { showCategoryDropdown = !showCategoryDropdown }) {
                        Text(text = selectedCategory)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = showCategoryDropdown,
                        onDismissRequest = { showCategoryDropdown = false }
                    ) {
                        listOf("Arte", "Teatro", "Música", "Deportes").forEach { category ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedCategory = category
                                    showCategoryDropdown = false
                                },
                                text = { Text(text = category) }
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.padding(16.dp).clickable { navController.navigate("crear_grupo") }, verticalAlignment = Alignment.CenterVertically) {
            Image(
                //imageVector = Icons.Default.Person,
                painter = painterResource(id = R.drawable.creargrupo),
                contentDescription = "Grupo Icon",
                modifier = Modifier
                    .size(50.dp)
                    //.clip(CircleShape)
                    .background(Color.Transparent)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Información del grupo
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Crear Grupo", style = MaterialTheme.typography.titleLarge.copy(), fontSize = 16.sp,fontWeight = FontWeight.Bold)
            }
        }

        // Lista de grupos
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(paginatedGrupos) { grupo ->
                GrupoCard(grupo)
            }
        }

        Button(
            onClick = {  navController.navigate("detalle_grupo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Buscar Grupos")
        }
        Button(
            onClick = {  navController.navigate("miembros_grupo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Ver miembros")
        }
        Button(
            onClick = {  navController.navigate("miembros_grupo_admin") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Eliminar miembros")
        }

        // Control de paginación
        Spacer(modifier = Modifier.height(8.dp))
        PaginationControl(
            currentPage = currentPage,
            totalPages = totalPages,
            onPreviousPage = { if (currentPage > 1) currentPage-- },
            onNextPage = { if (currentPage < totalPages) currentPage++ }
        )
        //Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun GrupoCard(grupo: Grupo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            // Imagen de grupo
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Grupo Icon",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Información del grupo
            Column(modifier = Modifier.weight(1f)) {
                Text(text = grupo.nombre, style = MaterialTheme.typography.titleMedium)
                Text(text = grupo.descripcion, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

fun generarGrupos(cantidad: Int): List<Grupo> {
    val nombres = listOf("Grupo Artístico", "Grupo Teatral", "100 años en la B", "Amigos académicos Ing de sistemas", "Grupo Futbol 11")
    val descripciones = listOf(
        "Evento musica",
        "Romeo y Julieta 2024 edition",
        "Torneo futsal mixto intercarreras",
        "Asesorias parcial estructuras de datos",
        "Grupo Futbol 11"
    )
    return List(cantidad) { i ->
        Grupo(
            nombre = nombres[i % nombres.size],
            descripcion = descripciones[i % descripciones.size]
        )
    }
}
