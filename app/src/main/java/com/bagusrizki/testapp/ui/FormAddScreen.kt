package com.bagusrizki.testapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bagusrizki.testapp.ui.data.User

@Composable
fun FormAddScreen ( userViewModel: UserViewModel = UserViewModel()){
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {
        OutlinedTextField(
            value = name,
            onValueChange = { newName -> name = newName },
            label = { Text(text = "Enter Your Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = username,
            onValueChange = { newUsername -> username = newUsername },
            label = { Text(text = "Enter Username") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { newPassword -> password = newPassword },
            label = { Text(text = "Enter Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val user = User(name = name, username = username, password = password)
                userViewModel.addUser(user)
            },
            modifier = Modifier
        ) {
            Text(text = "Submit")
        }
    }
}