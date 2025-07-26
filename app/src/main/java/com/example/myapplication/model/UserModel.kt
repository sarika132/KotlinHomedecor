package com.example.myapplication.model

data class UserModel(
    var userId: String = "",
    var email: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var gender: String = "",
    var address: String = "",
    val selectedOptionText: String,
)
