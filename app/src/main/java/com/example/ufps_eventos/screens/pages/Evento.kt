package com.example.ufps_eventos.screens.pages

import androidx.compose.ui.graphics.Color

data class Evento(
    val title: String,
    val description: String,
    val date: String,
    val status: String,
    val statusColor: Color
)
