package com.example.ufps_eventos.ui.screens.pages.groups

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ufps_eventos.R

@Composable
fun GroupCreatePage (navController: NavController, modifier: Modifier = Modifier) {
    GroupCreateScreen(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupCreateScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
        // Icono del grupo
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.google_logo),
                contentDescription = "Icono del grupo",
                modifier = Modifier.size(80.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "Agregar icono",
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(24.dp)
                    .background(Color(0xFF9C27B0), CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Título
        Text(
            text = "Nuevo Grupo",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Campo de texto para el nombre del grupo
        OutlinedTextField(
            value = "",
            onValueChange = { /* Aquí maneja el cambio de valor */ },
            label = { Text("Nombre del grupo") },
            placeholder = { Text("The Avengers, La Super-Patrulla...") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para la descripción
        OutlinedTextField(
            value = "",
            onValueChange = { /* Aquí maneja el cambio de valor */ },
            label = { Text("Descripción") },
            placeholder = { Text("¡Bienvenidos, este grupo está enfocado en...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            maxLines = 3
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para el cupo
        OutlinedTextField(
            value = "",
            onValueChange = { /* Aquí maneja el cambio de valor */ },
            label = { Text("Cupo (Máximo 200)") },
            placeholder = { Text("150, 10, 35...") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Selector de categorías
        var expanded by remember { mutableStateOf(false) }
        var selectedCategory by remember { mutableStateOf("Sin categoría") }
        val categories = listOf("Sin categoría", "Categoría 1", "Categoría 2")

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
                //Modifier.width(IntrinsicSize.Min)
            ) {
                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Seleccionar categorías") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .menuAnchor() // Asegura que el menú esté alineado
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                   //modifier = Modifier.fillMaxWidth()
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(text = category) },
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                selectedCategory = category
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
            //Spacer(modifier = Modifier.height(32.dp))

        // Botón de Crear
        Button(
            onClick = { /* Acción al crear el grupo */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(contentColor = Color(0xFF9C27B0))
        ) {
            Text("Crear", color = Color.White)
        }
    }
}
