package com.example.appkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class CriarAnuncio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criar_anuncio)

        val setaBack = findViewById<ImageView>(R.id.setaBack)
        val categorias = arrayOf("Esportes", "Games", "Eletronicos", "Roupas", "Brinquedos", "Outros")
        val autoCompleteTxt: AutoCompleteTextView = findViewById(R.id.autoCompleteTxt)
        val adapterItems = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, categorias)
        autoCompleteTxt.setAdapter(adapterItems)
        val imgView = findViewById<ImageView>(R.id.imgView)
        var nameProduct = findViewById<EditText>(R.id.nameProduct)
        var descriptionProduct = findViewById<EditText>(R.id.descriptionProduct)
        var priceProduct = findViewById<EditText>(R.id.priceProduct)
        var quantProduct = findViewById<EditText>(R.id.quantProduct)
        var btnPublicarAnuncio = findViewById<Button>(R.id.btnPublicarAnuncio)

        btnPublicarAnuncio.setOnClickListener{
            println(nameProduct.text)
            println(descriptionProduct.text)
            println(priceProduct.text)
            println(quantProduct.text)

            val produtoPostado = Intent( this, MainActivity::class.java)
            produtoPostado.putExtra("name", "${nameProduct.text}")
            startActivity(produtoPostado)
        }


        imgView.setOnClickListener{
            pickerMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        setaBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("showFragment", "AddItemFragment")
            startActivity(intent)
        }
    }

    private val pickerMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        val imgView = findViewById<ImageView>(R.id.imgView)
        imgView.setImageURI(uri)
        //val textAdicionarImg = findViewById<TextView>(R.id.textAdicionarImg)
        //textAdicionarImg.setText("")
    }

    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }

}