package com.example.appkotlin

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class MeusAnuncios : AppCompatActivity() {

    private val bancoDados by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_meus_anuncios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnAtualizar = findViewById<Button>(R.id.buttonAtualizar)
        val btnRemover = findViewById<Button>(R.id.btnRemover)
        btnAtualizar.setOnClickListener{
            atualizarDados()
        }

        btnRemover.setOnClickListener{
            removerDados()
        }
    }
    private fun atualizarDados() {
        val produto = mapOf(
            "nome" to "Mudei",
            "preço" to "Mudei2",
            "descrição" to "Mudei3"
        )

        val referenciaProduct = bancoDados
            .collection("produtos")
            .document("2")

        //Atualiza o produto
        referenciaProduct
            .update( "nome", "Atualizei" )
            .addOnSuccessListener {
                exibirMensagem("Produto atualizado com sucesso")
            }.addOnFailureListener{ exception ->
                exibirMensagem("Erro ao atualizar produto")
            }
    }

    private fun removerDados() {
        val produto = mapOf(
            "nome" to "Mudei",
            "preço" to "Mudei2",
            "descrição" to "Mudei3"
        )

        val referenciaProduct = bancoDados
            .collection("produtos")
            .document("2")

        //Remova o produto
        referenciaProduct
            .delete()
            .addOnSuccessListener {
                exibirMensagem("Produto removido com sucesso")
            }.addOnFailureListener{ exception ->
                exibirMensagem("Erro ao remover produto")
            }
    }

    private fun exibirMensagem(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
    }
}