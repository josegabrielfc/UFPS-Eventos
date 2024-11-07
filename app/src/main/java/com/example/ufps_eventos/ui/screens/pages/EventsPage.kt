package com.example.ufps_eventos.ui.screens.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ufps_eventos.model.Evento
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarSelection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsPage(navController: NavController, modifier: Modifier = Modifier) {
    var selectedGroup by remember { mutableStateOf("Grupo") }
    var showGroupDropdown by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var calendarState = rememberSheetState()

    CalendarDialog(
        state = calendarState,
        selection = CalendarSelection.Date{
                date -> Log.d("SelectedDate", "$date")
        }
    )

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // Barra superior
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Tus Eventos", style = MaterialTheme.typography.titleLarge)

            Row {
                Box {
                    TextButton(onClick = { showGroupDropdown = !showGroupDropdown }) {
                        Text(text = selectedGroup)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = showGroupDropdown,
                        onDismissRequest = { showGroupDropdown = false }
                    ) {
                        listOf(
                            "Grupo Artístico",
                            "Grupo Teatral",
                            "100 años en la B",
                            "Grupo Futbol 11"
                        ).forEach { group ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedGroup = group
                                    showGroupDropdown = false
                                },
                                text = { Text(text = group) }
                            )
                        }
                    }

                }

                // Calendario
                IconButton(onClick = {
                    //showDatePicker = true
                    calendarState.show()
                }) {
                    Icon(Icons.Default.DateRange, contentDescription = "Calendario")
                }
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        // Barra de búsqueda
        OutlinedTextField(
            value = "",
            onValueChange = {  },
            placeholder = { Text(text = "Hinted search text") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        EventListView(navController = navController)
    }
}


fun generarEventos(cantidad: Int): List<Evento> {
    val listaDeEventos = mutableListOf<Evento>()
    val colores = listOf(
        Color(0xFFFFD700), // Dorado
        Color(0xFFFF6347), // Rojo tomate
        Color(0xFF8A2BE2), // Azul violeta
        Color(0xFF32CD32), // Verde lima
        Color(0xFF00CED1)  // Turquesa oscuro
    )

    for (i in 1..cantidad) {
        val titulo = "Evento $i"
        val descripcion = "Descripción del evento $i"
        val fecha = "14/${11 + (i % 12)}/2024"
        val estado = "${15 + (i % 20)} / 35"
        val color = colores[i % colores.size]

        listaDeEventos.add(Evento(titulo, descripcion, fecha, estado, color))
    }

    return listaDeEventos
}

@Composable
fun EventListView(navController: NavController) {

    var currentPage by remember { mutableStateOf(1) }
    val itemsPerPage = 5

    val events = generarEventos(15)
    /*val events = listOf(
        Evento("Evento 1", "Descripción del evento", "13/11/2024", "15 / 35", Color(0xFFFFD700)),
        Evento("Evento 2", "Descripción del evento 2", "14/11/2024", "19 / 35", Color(0xFFFF6347)),
        Evento("Evento 3", "Descripción del evento 3", "14/11/2024", "20 / 35", Color(0xFFFF6347)),
        Evento("Evento 4", "Descripción del evento 4", "14/11/2024", "19 / 35", Color(0xFFFF6347)),
        Evento("Evento 5", "Descripción del evento 5", "14/11/2024", "20 / 35", Color(0xFFFF6347)),
        Evento("Evento 6", "Descripción del evento 6", "14/11/2024", "19 / 35", Color(0xFFFF6347)),
        Evento("Evento 7", "Descripción del evento 7", "14/11/2024", "20 / 35", Color(0xFFFF6347))
    )*/
    val totalPages = (events.size + itemsPerPage - 1) / itemsPerPage

    // Eventos de la página actual
    val paginatedEvents = events.drop((currentPage - 1) * itemsPerPage).take(itemsPerPage)


    // Listado de Eventos
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(paginatedEvents) { event ->
                EventCard(event)
            }
        }

        // Control de paginación
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("event_detail_page") }) {
            
        }
        PaginationControl(
            currentPage = currentPage,
            totalPages = totalPages,
            onPreviousPage = { if (currentPage > 1) currentPage-- },
            onNextPage = { if (currentPage < totalPages) currentPage++ }
        )

        Spacer(modifier = Modifier.height(65.dp))
    }
}

@Composable
fun EventCard(evento: Evento) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = evento.title, style = MaterialTheme.typography.titleMedium)
            Text(text = evento.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Fecha: ${evento.date}", style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = evento.status,
                    color = evento.statusColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Button(
                    onClick = { /* Acción para "Ver más" */ },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(text = "Ver más")
                }
            }
        }
    }
}

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
