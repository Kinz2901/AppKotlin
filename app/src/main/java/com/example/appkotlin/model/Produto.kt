package com.example.appkotlin.model

data class Produto(
    val imagem: String,           // URL ou caminho da imagem
    val nome: String,
    val descricao: String,
    val preco: Double,
    val quantidade: Int,
    val categoria: String
)