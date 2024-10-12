package com.example.appkotlin


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ReportFragment.Companion.reportFragment


class CriarAnuncio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criar_anuncio)


        val setaBack = findViewById<ImageView>(R.id.setaBack)

        setaBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("showFragment", "AddItemFragment")
            startActivity(intent)
        }

    }
    private fun replaceFragment(fragment : Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }

}