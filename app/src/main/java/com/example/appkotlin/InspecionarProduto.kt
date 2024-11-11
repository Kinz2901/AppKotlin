package com.example.appkotlin

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class InspecionarProduto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()





        setContentView(R.layout.activity_inspecionar_produto)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    override fun onStart() {
        super.onStart()

        val image = intent.getStringExtra("Imagem")
        val nome = intent.getStringExtra("Nome")
        val preco = intent.getStringExtra("Preco")
        val descricao = intent.getStringExtra("Descricao")
        val categoria = intent.getStringExtra("Categoria")
        val quantidade = intent.getStringExtra("Quantidade")

        val newImage = findViewById<ImageView>(R.id.imageProduto)
        val newPreco = findViewById<TextView>(R.id.precoProduto)
        val newCategoria = findViewById<TextView>(R.id.categoriaProduto)
        val newNome = findViewById<TextView>(R.id.nomeProduto)
        val newDescricao = findViewById<TextView>(R.id.descricaoProduto)
        val newQuantidade = findViewById<TextView>(R.id.quantProduto)

        val uri = image
        Glide.with(newImage.context)
            .load(uri)
            .into(newImage)
        newPreco.text = "R$ $preco"
        newCategoria.text = categoria
        newNome.text = nome
        newDescricao.text = descricao
        val strQuant = quantidade.toString()
        newQuantidade.text = "Quantidades disponíveis: $strQuant"

    }
}