package com.example.appkotlin
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore

class CriarAnuncio : AppCompatActivity() {

    private val bancoDados by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criar_anuncio)

        val setaBack = findViewById<ImageView>(R.id.setaBack)
        val categorias = arrayOf("Esportes", "Games", "Eletronicos", "Roupas", "Brinquedos", "Outros")
        val autoCompleteTxt: AutoCompleteTextView = findViewById(R.id.autoCompleteTxt)
        val adapterItems = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categorias)
        autoCompleteTxt.setAdapter(adapterItems)
        val imgView = findViewById<ImageView>(R.id.imgView)
        val nameProduct = findViewById<EditText>(R.id.nameProduct)
        val descriptionProduct = findViewById<EditText>(R.id.descriptionProduct)
        val priceProduct = findViewById<EditText>(R.id.priceProduct)
        val quantProduct = findViewById<EditText>(R.id.quantProduct)
        val btnPublicarAnuncio = findViewById<Button>(R.id.btnPublicarAnuncio)

        btnPublicarAnuncio.setOnClickListener{
            val nome = nameProduct.text.toString()
            val preco = priceProduct.text.toString().toDouble()
            val img = imgView.drawable
            val descricao = descriptionProduct.text.toString()
            val quantidade = quantProduct.text.toString().toInt()

            salvarDados(nome, descricao, preco, quantidade)

        }


        imgView.setOnClickListener {
            pickerMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        setaBack.setOnClickListener {
            finish()
        }
    }

    private val pickerMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        val imgView = findViewById<ImageView>(R.id.imgView)
        imgView.setImageURI(uri)
    }

    private fun salvarDados(nome: String, descricao: String, preco: Double, quantidade: Int) {


        val produto = mapOf(
            "nome" to nome,
            "preço" to preco,
            "descrição" to descricao,
            "quantidade" to quantidade
        )

        val produtos = bancoDados
            .collection("produtos")

        produtos
            .add(produto)
            .addOnSuccessListener {
                exibirMensagem("Produto salvo com sucesso")
            }.addOnFailureListener{ exception ->
                exibirMensagem("Erro ao salvar produto")
            }
    }
    private fun exibirMensagem(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
    }

}