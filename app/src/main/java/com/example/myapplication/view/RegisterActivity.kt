package com.example.myapplication.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.UserModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.repository.UserRepositoryImp
import com.example.myapplication.viewmodel.UserViewModel

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { innerPadding ->
                RegBody(innerPadding)
            }
        }
    }
}

@Composable
fun RegBody(innerPadding: PaddingValues) {
    val repo = remember { UserRepositoryImp() }
    val userViewModel = remember { UserViewModel(repo) }

    val context = LocalContext.current
    val activity = context as? Activity

    var name by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var agreedToTerms by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 10.dp)
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Create Your Account Here", color = Color.Black)
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = agreedToTerms,
                onCheckedChange = { agreedToTerms = it }
            )
            Text(text = "I agree to the Terms and Conditions.")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (name.isBlank() || phoneNumber.isBlank() || email.isBlank()
                    || password.isBlank() || confirmPassword.isBlank()
                ) {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if (password != confirmPassword) {
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                if (!agreedToTerms) {
                    Toast.makeText(context, "You must agree to the terms", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                userViewModel.register(email, password) { success, message, userId ->
                    if (success) {
                        val userModel = UserModel(userId, name, email, password ,)
                        userViewModel.addUserToDatabase(userId, userModel) { success, msg ->
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SIGN UP")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Already have an account? Login",
            color = Color.Magenta,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {
                    context.startActivity(Intent(context, LoginActivity::class.java))
                    activity?.finish()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegPreview() {
    RegBody(innerPadding = PaddingValues(0.dp))
}
