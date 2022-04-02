package com.carbondev.banking.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.carbondev.banking.ui.theme.Purple700

@Composable
fun SearchSponsorScreen(
    navController: NavController,
    viewModel: RegistrationViewModel
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = viewModel.sponsorId,
            onValueChange = { data: String ->
                viewModel.sponsorId = data
                viewModel.sponsorQuery(data)
            },
            label = { Text("sponsor id") },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "",
                    tint = Purple700
                )
            },
            trailingIcon = {
                IconButton(onClick = {
                    viewModel.sponsorId = ""
                    viewModel.sponsorQuery("")
                }) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "",
                        tint = Purple700
                    )
                }
            },
            colors = fieldColor()
        )
        LazyColumn {
            items(viewModel.sponsorIdPrediction) { sid ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable {
                        viewModel.sponsorId = sid
                    }
                ) {
                    Text(text = sid, fontSize = 20.sp, modifier = Modifier.padding(8.dp))
                }
            }
        }
        Button(onClick = {
            navController.navigateUp()
        }) {
            Text(text = "Conform")
        }
    }
}