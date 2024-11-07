package com.example.ufps_eventos.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ufps_eventos.R
import com.example.ufps_eventos.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navController: NavController){
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
fun BodyLoginContent(navController: NavController) {
    val googleLogo: Painter = painterResource(id = R.drawable.google_logo)

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
                .clip(CardDefaults.shape),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF3F9FA)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Hola \uD83D\uDC4B",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(top = 12.dp, bottom = 8.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Bienvenido a UFPS Eventos. Para continuar, por favor inicia sesión con tu cuenta de Google.",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 15.sp,
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(48.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                                  navController.navigate(route = AppScreens.MainScreen.route)
                                  //navController.navigate(route = AppScreens.SecondScreen.route + "/Parametro")
                        },
                        modifier = Modifier
                            .width(250.dp)
                            .height(50.dp)
                            .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
                            .padding(horizontal = 4.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEFF2F5))
                    ) {
                        // Contenido del botón: Imagen y texto
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = googleLogo,
                                contentDescription = "Google Logo",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Google", color = Color(0xFF3C4043))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(28.dp))
            }
        }
    }
}
