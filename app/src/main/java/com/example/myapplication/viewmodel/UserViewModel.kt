package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.UserModel
import com.google.firebase.auth.FirebaseUser
import com.example.myapplication.repository.UserRepository

class UserViewModel(val repo: UserRepository) : ViewModel() {


        //database function
        fun addUserToDatabase(
            userId: String, model: UserModel,
            callback: (Boolean, String) -> Unit
        ){
            repo.addUserToDatabase(userId, model,callback)
        }

        fun updateProfile(userId: String,data : MutableMap<String,Any?>,
                          callback: (Boolean, String) -> Unit){
            repo.updateProfile(userId,data,callback)
        }

        fun forgetPassword(
            email: String, callback: (Boolean, String) -> Unit
        ){
            repo.forgetPassword(email, callback)
        }

        fun getCurrentUser(): FirebaseUser?{
            return  repo.getCurrentUser()
        }

        private  val _users = MutableLiveData<UserModel?>()
        val users: LiveData<UserModel?> get() = _users

        fun getUserById(
            userId: String,
        ){
            repo.getUserById(userId){ users,success,message ->
                if (success && users != null){
                    _users.postValue(users)
                }else{
                    _users.postValue(null)
                }
            }

        }

        fun logout(callback: (Boolean, String) -> Unit){
            repo.logout(callback)
        }
    }
