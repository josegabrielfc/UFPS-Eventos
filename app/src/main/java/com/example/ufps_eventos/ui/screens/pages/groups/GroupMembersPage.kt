package com.example.ufps_eventos.ui.screens.pages.groups

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ufps_eventos.R
import com.example.ufps_eventos.ui.screens.pages.PaginationControl

@Composable
fun GroupMembersPage(navController: NavController){
    GroupMembersScreen(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupMembersScreen(
    navController: NavController,
    groupName: String = "100 años en la B",
    membersCount: Int = 50,
    members: List<String> = listOf(
        "Julian Alvarez", "Josep Guardiola | Sala", "Literalmente un cucuteño fan son mas que palabras, para ver que lo que",
        "Jose Gabriel Chona", "Luis Carlos Asencio", "Colombia Humana", "El inge    ", "Otro nombre", "more names"
    )
) {
    var currentPage by remember { mutableStateOf(1) }
    val itemsPerPage = 7
    val totalPages = (members.size + itemsPerPage - 1) / itemsPerPage
    val paginatedMembers = members.drop((currentPage - 1) * itemsPerPage).take(itemsPerPage)

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
            text = "$groupName - Miembros",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = "$membersCount miembros",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de miembros
        LazyColumn {
            items(paginatedMembers) { member ->
                MemberItem(memberName = member)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        PaginationControl(
            currentPage = currentPage,
            totalPages = totalPages,
            onPreviousPage = { if (currentPage > 1) currentPage-- },
            onNextPage = { if (currentPage < totalPages) currentPage++ }
        )
    }
}

@Composable
fun MemberItem(memberName: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Box (
                modifier = Modifier.padding(horizontal = 10.dp),
            ){
                Image(
                    painter = painterResource(id = R.drawable.google_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = memberName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}
