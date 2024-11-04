package com.example.ufps_eventos.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ufps_eventos.R
import com.example.ufps_eventos.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Template(navController: NavController){
    val backgroundImage: Painter = painterResource(id = R.drawable.login_background)

    Scaffold {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        BodyLoginContent(navController)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BodyTemplateContent(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Estructura estática basada en ReplyEmailListItem
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .semantics { selected = false }
                .clip(CardDefaults.shape)
                .combinedClickable(
                    onClick = { /* Navegar a detalle estático */ },
                    onLongClick = { /* Toggle de selección estático */ }
                )
                .clip(CardDefaults.shape),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    val clickModifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) { /* Acción estática de selección */ }
                    // Imagen de perfil estática
                    /*AnimatedContent(targetState = false, label = "avatar") { selected ->
                        ReplyProfileImage(
                            avatar = "static_avatar_url", // URL de avatar estática
                            fullName = "John Doe", // Nombre estático
                            modifier = clickModifier
                        )
                    }*/

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "John", // Nombre estático
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = "2023-11-04", // Fecha estática
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                    IconButton(
                        onClick = { /* Acción del botón estático */ },
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Favorite",
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }
                }

                Text(
                    text = "Static Subject", // Asunto estático
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                )
                Text(
                    text = "This is a static preview of the email body.", // Cuerpo del correo estático
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        // Botón de Navegación original
        Spacer(modifier = Modifier.height(16.dp))
        Text("Hola Mundo")
        Button(onClick = {
            navController.navigate(route = AppScreens.SecondScreen.route + "/Parametro")
        }) {
            Text("Navegar :D")
        }
    }
}
