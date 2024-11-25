package com.example.ufps_eventos.ui.screens.pages.groups.admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ufps_eventos.R
import androidx.compose.foundation.layout.*

@Composable
fun GroupAdminDetailPage(navController: NavController, modifier: Modifier = Modifier) {
    GroupAdminDetailScreen(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupAdminDetailScreen(navController: NavController,
    groupName: String = "100 años en la B",
    adminName: String = "Josep Guardiola | Sala",
    description: String = "Bienvenidos todos, este es un grupo enfocado a debatir sobre la situación actual del equipo...",
    categories: List<Pair<String, Color>> = listOf(
        "Académico" to Color(0xFFF3B5A8),
        "Fútbol" to Color(0xFF88CDF6),
        "Debate" to Color(0xFFF5BBF5)
    ),
    onLeaveGroup: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            modifier = Modifier.background(Color(0xFFF3F9FA))
        )
        Text(
            text = groupName,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Image(
            painter = painterResource(id = R.drawable.google_logo), // Cambia esta URL a la imagen del grupo
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Categorías
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            categories.forEach { (category, color) ->
                Text(
                    text = category,
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .background(color, RoundedCornerShape(16.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .padding(end = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Admin del grupo
        Text(
            text = adminName,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    color = Color(0xFFE0E0E0),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        ){
        // Descripción
            Column {
                Text(
                    text = "Descripción: ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón "Salir del Grupo"
        Button(
            onClick = { navController.navigate("miembros_grupo_admin") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Editar Miembros", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Botón "Salir del Grupo"
        Button(
            onClick = { navController.navigate("editar_grupo") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Editar Información", color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Botón "Salir del Grupo"
        Button(
            onClick = onLeaveGroup,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Eliminar Grupo", color = Color.White)
        }
    }
}
