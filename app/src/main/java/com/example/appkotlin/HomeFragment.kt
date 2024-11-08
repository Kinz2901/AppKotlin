package com.example.appkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appkotlin.model.Produto
import com.example.appkotlin.view.ProdutoAdapter
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {
    private lateinit var produtoAdapter: ProdutoAdapter

    private lateinit var lista: RecyclerView
    private lateinit var btnAtualizar: Button

    private val bancoDados by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }


    override fun onStart() {
        super.onStart()
        adicionarListenerContatos()
    }

    private fun adicionarListenerContatos() {
        bancoDados
            .collection("produtos")
            .addSnapshotListener { querySnapshot, error ->
                val documentos = querySnapshot?.documents
                documentos?.forEach { documentSnapshot ->

                    val produto = documentSnapshot.toObject( Produto::class.java )

                    if( produto != null ) {
                        Log.i("fragment_home", "nome: ${produto.nome}")
                    }
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}