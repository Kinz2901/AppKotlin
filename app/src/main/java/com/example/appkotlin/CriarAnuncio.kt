package com.example.appkotlin
import android.content.Intent
import android.net.Uri
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
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class CriarAnuncio : AppCompatActivity() {

    private val bancoDados by lazy {
        FirebaseFirestore.getInstance()
    }

    private val armazenamento by lazy {
        FirebaseStorage.getInstance()
    }

    private var imageUri: Uri? = null

    private val pickerMedia = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            imageUri = uri
            val imgView = findViewById<ImageView>(R.id.imgView)
            imgView.setImageURI(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criar_anuncio)

        val setaBack = findViewById<ImageView>(R.id.setaBack)
        val categorias = arrayOf("Esportes", "Games", "Eletronicos", "Roupas", "Brinquedos", "Outros")
        val autoCompleteText: AutoCompleteTextView = findViewById(R.id.autoCompleteTxt)
        val adapterItems = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categorias)
        autoCompleteText.setAdapter(adapterItems)
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
            val categoria = autoCompleteText.text.toString()

            uploadGaleria(nome, descricao, preco, quantidade, categoria)
        }


        imgView.setOnClickListener {
            pickerMedia.launch("image/*")
        }

        setaBack.setOnClickListener {
            finish()
        }
    }


    val nomeImagem = UUID.randomUUID().toString()
    private fun uploadGaleria(nome: String, descricao: String, preco: Double, quantidade: Int, categoria: String) {
        if ( imageUri != null ) {
            armazenamento
                .getReference("produtos")
                .child(nomeImagem)
                .putFile( imageUri!! )
                .addOnSuccessListener { task ->
                    exibirMensagem("Sucesso ao fazer upload da imagem")
                    task.metadata?.reference?.downloadUrl
                        ?.addOnSuccessListener { uriFirebase ->
                            salvarDados(uriFirebase.toString(), nome, descricao, preco, quantidade, categoria)
                        }
                }.addOnFailureListener{ erro ->
                    exibirMensagem("Erro ao fazer upload da imagem")
                }
        }
    }

    private fun salvarDados(imagemUrl: String, nome: String, descricao: String, preco: Double, quantidade: Int, categoria: String) {

        val produto = mapOf(
            "imagemUrl" to imagemUrl,
            "nome" to nome,
            "preco" to preco,
            "descricao" to descricao,
            "quantidade" to quantidade,
            "categoria" to categoria
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