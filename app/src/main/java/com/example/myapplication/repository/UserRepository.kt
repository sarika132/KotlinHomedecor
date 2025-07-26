package com.example.myapplication.repository

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.appcompat.view.ActionMode.Callback
import com.example.myapplication.model.UserModel
import com.google.firebase.auth.FirebaseUser

interface UserRepository {
    //login
    //register
    //forgetPassword
    //UpdateProfile
    //getCurrentUser
    //getUserById
    //addUserToDatabase
    //logout

    //    {
//        "success" : true,
//        "message" : "Registration success",
//    "userId":"dafsgdhfdsfa"
//    }
    fun login(
        email: String, password: String,
        callback: (Boolean, String) -> Unit
    )

    //authentication function
    fun register(
        email: String, password: String,
        callback: (Boolean, String, String) -> Unit
    )

    //database function
    fun addUserToDatabase(
        userId: String, model: UserModel,
        callback: (Boolean, String) -> Unit
    )

    fun updateProfile(
        userId: String, data: MutableMap<String, Any?>,
        callback: (Boolean, String) -> Unit
    )

    fun forgetPassword(
        email: String, callback: (Boolean, String) -> Unit
    )

    fun getCurrentUser(): FirebaseUser?

    fun getUserById(userId: String, callback: (UserModel?, Boolean, String) -> Unit)

    fun logout(callback: (Boolean, String) -> Unit)
}