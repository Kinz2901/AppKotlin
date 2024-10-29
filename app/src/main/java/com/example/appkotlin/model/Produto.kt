package com.example.appkotlin.model

data class Produto(     // URL ou caminho da imagem
    val imagem: Int,
    val nome: String,
    val preco: String,
    val categoria: String
)