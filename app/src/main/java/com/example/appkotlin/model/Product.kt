package com.example.appkotlin.model
import android.net.Uri
import androidx.annotation.DrawableRes
import java.net.URL
import java.util.UUID

data class Product(
    val nameProduct: String,
    val imageUri: String,
    val priceProduct: Double,

    /** val imgProduct: Uri?,
    val quantProduct: String?,
    val descriptionProduct: String?,
    */
)