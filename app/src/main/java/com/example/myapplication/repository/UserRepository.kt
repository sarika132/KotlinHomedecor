package com.example.myapplication.repository

import com.example.myapplication.model.UserModel
import com.google.firebase.auth.FirebaseUser
import android.net.wifi.aware.AttachCallback
import android.content.Context


    //login
    // Register
    // forget password
    //update profile
    //update profile
    //get CurrentUser
    // addUserToDatabase
    //logout


    //database function

    interface UserRepository {
        fun login(email: String, password: String, callback: (Boolean, String) -> Unit)
        fun register(email: String, password: String, callback: (Boolean, String, String) -> Unit)
        fun addUserToDatabase(userId: String, model: UserModel, callback: (Boolean, String) -> Unit)
        fun updateProfile(userId: String, data: MutableMap<String, Any?>, callback: (Boolean, String) -> Unit)
        fun forgetPassword(email: String, callback: (Boolean, String) -> Unit)
        fun getCurrentUser(): FirebaseUser?
        fun getUserById(userId: String, callback: (UserModel?, Boolean, String) -> Unit)
        fun logout(callback: (Boolean, String) -> Unit)
    }


