package com.example.myapplication.viewmodel
import androidx.lifecycle.ViewModel
import com.example.myapplication.repository.AuthRepository

class AuthViewModel (val repo: AuthRepository) : ViewModel() {


        fun login(
            email: String, password: String,
            callback: (Boolean, String) -> Unit
        ){
            repo.login(email,password,callback)
        }

        //authentication function
        fun register(
            email: String, password: String,
            callback: (Boolean, String, String) -> Unit
        ){
            repo.register(email, password, callback)
        }

}