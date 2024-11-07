package com.example.appkotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        verificarUsuarioLogado()
    }

    private fun verificarUsuarioLogado() {
        //autenticacao.signOut()
        val usuario = autenticacao.currentUser
        val id = usuario?.uid

        if( usuario != null ){
            exibirMensagem("Usuário está logado")
            val fragmentToShow = intent.getStringExtra("showFragment")

            if (fragmentToShow == "AddItemFragment") {
                replaceFragment(AddItemFragment())
            } else {
                replaceFragment(HomeFragment())
            }
        } else {
            exibirMensagem("Não tem usuário logado")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logar = findViewById<Button>(R.id.logar)

        logar.setOnClickListener{
            logarUsuario()
        }

        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.profile -> replaceFragment(ProfileFragment())
                R.id.addItem -> replaceFragment(AddItemFragment())

                else -> {

                }

            }
            true
        }

    }

    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }

    private fun logarUsuario() {
        val email = "guilhermelins9782@gmail.com"
        val senha = "1234teste@"

        //Estivesse em uma tela de login

        autenticacao.signInWithEmailAndPassword(
            email, senha
        ).addOnSuccessListener { authResult ->
            exibirMensagem("Usuário logado com sucesso")
        }.addOnFailureListener { exception ->
            val mensagemErro = exception.message
            exibirMensagem("Erro no login do usuário")
        }
    }

    private fun cadastrarUsuario() {
        //Dados digitados pelo usuário
        val email = "guilhermelins9782@gmail.com"
        val senha = "1234teste@"

        val autenticacao = FirebaseAuth.getInstance()
        autenticacao.createUserWithEmailAndPassword(
            email, senha
        ).addOnSuccessListener { authResult ->
            val email = authResult.user?.email
            val id = authResult.user?.uid
            exibirMensagem("Sucesso: $id - $email")
        }.addOnFailureListener { exception ->
            val mensagemErro = exception.message
            exibirMensagem("Erro: $mensagemErro")
        }
    }

    private fun exibirMensagem(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
    }

}