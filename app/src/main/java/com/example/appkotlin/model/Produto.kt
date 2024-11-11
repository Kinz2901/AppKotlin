package com.example.appkotlin.model

import com.example.appkotlin.R

data class Produto(
    val imagemUrl: String = "",
    val nome: String = "",
    val preco: Double = 0.0,
    val descricao: String = "",
    val quantidade: Int = 0,
    val categoria: String = ""
)