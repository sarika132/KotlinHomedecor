package com.example.myapplication.product

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.google.firebase.firestore.FirebaseFirestore

class UpdateProductActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var priceEditText: EditText
    private lateinit var descEditText: EditText
    private lateinit var imageEditText: EditText
    private lateinit var updateButton: Button

    private lateinit var firestore: FirebaseFirestore
    private lateinit var productId: String  // received from intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_product)

        nameEditText = findViewById(R.id.etProductName)
        priceEditText = findViewById(R.id.etProductPrice)
        descEditText = findViewById(R.id.etProductDescription)
        imageEditText = findViewById(R.id.etProductImageUrl)
        updateButton = findViewById(R.id.btnUpdateProduct)

        firestore = FirebaseFirestore.getInstance()

        // Get product ID and data from Intent
        productId = intent.getStringExtra("productId") ?: return
        val productName = intent.getStringExtra("productName") ?: ""
        val productPrice = intent.getStringExtra("productPrice") ?: ""
        val productDesc = intent.getStringExtra("productDesc") ?: ""
        val productImage = intent.getStringExtra("productImage") ?: ""

        // Pre-fill fields
        nameEditText.setText(productName)
        priceEditText.setText(productPrice)
        descEditText.setText(productDesc)
        imageEditText.setText(productImage)

        updateButton.setOnClickListener {
            val updatedName = nameEditText.text.toString().trim()
            val updatedPrice = priceEditText.text.toString().trim()
            val updatedDesc = descEditText.text.toString().trim()
            val updatedImage = imageEditText.text.toString().trim()

            if (updatedName.isEmpty() || updatedPrice.isEmpty() || updatedDesc.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                val productMap = mapOf(
                    "name" to updatedName,
                    "price" to updatedPrice,
                    "description" to updatedDesc,
                    "imageUrl" to updatedImage
                )

                firestore.collection("products")
                    .document(productId)
                    .update(productMap)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Product updated successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}
