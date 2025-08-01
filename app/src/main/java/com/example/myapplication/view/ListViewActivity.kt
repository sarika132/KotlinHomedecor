//package com.example.myapplication.product
//
//import android.os.Bundle
//import android.widget.ListView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.myapplication.R
//import com.google.firebase.firestore.FirebaseFirestore
//
//class ListViewActivity : AppCompatActivity() {
//
//    private lateinit var listView: ListView
//    private lateinit var productList: ArrayList<ProductModel>
//    private lateinit var adapter: ProductAdapter
//    private val db = FirebaseFirestore.getInstance()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_list_view)
//
//        listView = findViewById(R.id.listView)
//        productList = ArrayList()
//
//        fetchProducts()
//    }
//
//    private fun fetchProducts() {
//        db.collection("products")
//            .get()
//            .addOnSuccessListener { result ->
//                productList.clear()
//                for (doc in result) {
//                    val product = doc.toObject(ProductModel::class.java).copy(id = doc.id)
//                    productList.add(product)
//                }
//                adapter = ProductAdapter(this, productList)
//                listView.adapter = adapter
//            }
//            .addOnFailureListener {
//                Toast.makeText(this, "Failed to fetch products", Toast.LENGTH_SHORT).show()
//            }
//    }
//}
