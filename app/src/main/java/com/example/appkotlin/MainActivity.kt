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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private val autenticacao by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    private fun verificarUsuário() {
        val usuario = autenticacao.currentUser
        val id = usuario?.uid

        if ( usuario != null) {
            setContentView(R.layout.activity_main)
            val fragmentToShow = intent.getStringExtra("showFragment")

            if (fragmentToShow == "AddItemFragment") {
                replaceFragment(AddItemFragment())
            } else {
                replaceFragment(HomeFragment())
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
        }else{
            // Redireciona para a LoginActivity se o usuário não estiver logado
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Finaliza a MainActivity para evitar que o usuário volte sem login
        }
    }

    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }

    private fun cadastrarUsuários() {
        val email = "teste.admin@gmail.com"
        val senha = "admin123@"

        autenticacao.createUserWithEmailAndPassword(
            email, senha
        )
    }
    private fun exibirMensagem(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
    }

    private fun deslogarUsuario() {
        autenticacao.signOut()
    }

    override fun onStart() {
        super.onStart()
        verificarUsuário()
    }
}