package com.bagusrizki.testapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen( userViewModel: UserViewModel = UserViewModel()) {

    // Collect the list of users from ViewModel
    val userList by userViewModel.userList.collectAsState()

    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        // Display the list of users
        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(userList) { user ->
                ItemCardUser(name = "Name: ${user.name}", username = "Username: ${user.username}")
            }
        }
    }
}


@Composable
fun ItemCardUser(name: String, username: String) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(text = name)
        Text(text = username)
    }
}
