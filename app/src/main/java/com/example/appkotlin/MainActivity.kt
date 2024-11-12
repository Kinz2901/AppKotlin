package com.example.appkotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // Verifica se o usuário está logado
        val sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (!isLoggedIn) {
            // Redireciona para a LoginActivity se o usuário não estiver logado
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Finaliza a MainActivity para evitar que o usuário volte sem login
        }

        // Resto da lógica da MainActivity

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

    }

    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }



}