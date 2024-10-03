package com.bagusrizki.testapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bagusrizki.testapp.ui.data.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference.child("users")

    // Mutable state to hold the list of users
    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList: StateFlow<List<User>> = _userList

    init {
        // Automatically read data when the ViewModel is created
        viewModelScope.launch {
            readUsers()
        }
    }

    // Function to add a new user
    fun addUser(user: User) {
        val userId = database.push().key // Generate a unique ID for the user
        userId?.let {
            database.child(it).setValue(user)
        }
    }

    // Function to read users from Firebase
    private fun readUsers() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val users = mutableListOf<User>()
                for (data in snapshot.children) {
                    val user = data.getValue(User::class.java)
                    if (user != null) {
                        users.add(user)
                    }
                }
                _userList.value = users // Update the list of users
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors if needed
            }
        })
    }

}
