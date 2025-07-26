package com.example.myapplication.repository

import android.adservices.adid.AdId
import android.content.Context
import android.hardware.display.DeviceProductInfo
import android.net.Uri
import com.example.myapplication.model.ProductModel

interface ProductRepository {

    //addProduct
    //updateProduct
    //deleteProduct
    //getProductById
    //getAllProducts
    //searchProducts
    //getProductsByCategory
    //addProductReview
    //getProductReviews




    /*
            success : true,
            message : "product deleted succesfully"
             */
    fun addProduct(
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    )

    fun updateProduct(
        productId: String,
        data: MutableMap<String, Any?>,
        callback: (Boolean, String) -> Unit
    )

    fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    )

    /*
  success : true,
  message : "product fetched succesfully"
   */
    fun getProductById(
        productId: String,
        callback: (Boolean, String, ProductModel?) -> Unit
    )

    fun getAllProduct(callback: (Boolean, String,
                                 List<ProductModel?>) -> Unit)

    fun uploadImage(context: Context, imageUri: Uri, callback: (String?) -> Unit)

    fun getFileNameFromUri(context: Context,uri: Uri): String?


    //present - true
    //absent - false
//    fun attendance(name:String,callback: (Boolean) -> Unit)
}