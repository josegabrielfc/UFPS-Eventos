package com.example.ufps_eventos.ui.screens.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PaginationControl(
    currentPage: Int,
    totalPages: Int,
    onPreviousPage: () -> Unit,
    onNextPage: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onPreviousPage,
            enabled = currentPage > 1 // Deshabilitar si estamos en la primera página
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Página anterior")
        }

        Text(text = "$currentPage / $totalPages")

        IconButton(
            onClick = onNextPage,
            enabled = currentPage < totalPages // Deshabilitar si estamos en la última página
        ) {
            Icon(Icons.Default.ArrowForward, contentDescription = "Página siguiente")
        }
    }
}