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
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentToShow = intent.getStringExtra("showFragment")
        if (fragmentToShow == "AddItemFragment") {
            replaceFragment(AddItemFragment())
        } else {
            replaceFragment(HomeFragment())
        }


        val nameNewProduct = findViewById<TextView>(R.id.nameNewProduct)

       



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