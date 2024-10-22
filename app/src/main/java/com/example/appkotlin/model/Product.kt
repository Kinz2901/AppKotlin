package com.example.appkotlin.model
import androidx.annotation.DrawableRes
import java.util.UUID

data class Product(
    val id: UUID = UUID.randomUUID(),
    val nameProduct: String,
    val descriptionProduct: String,
    val priceProduct: String,
    val quantProduct: String,
    @DrawableRes val imgProduct: Int?
)