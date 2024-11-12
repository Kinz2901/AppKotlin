package com.example.appkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun logarUsuario() {

        val email = "teste.admin@gmail.com"
        val senha = "admin123@"

        autenticacao.signInWithEmailAndPassword(
            email,
            senha
        ).addOnSuccessListener { authResult ->
            exibirMensagem("Sucesso ao logar usuário")
        }.addOnFailureListener { exception ->
            exibirMensagem("Falha ao logar usuário")
        }
    }

    private fun exibirMensagem(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        val btnLogar = findViewById<Button>(R.id.btnLogar)

        val email = findViewById<EditText>(R.id.email)
        val senha = findViewById<EditText>(R.id.senha)

        val emailText = email.text.toString()
        val senhaText = senha.text.toString()


        btnLogar.setOnClickListener{
            logarUsuario()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}