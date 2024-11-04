package com.example.appkotlin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appkotlin.model.Produto
import com.example.appkotlin.view.ProdutoAdapter

class HomeFragment : Fragment() {
    private lateinit var produtoAdapter: ProdutoAdapter

    private lateinit var lista: RecyclerView
    private lateinit var btnAtualizar: Button

    private var imagem: Int? = null
    private var nome: String? = null
    private var preco: String? = null
    private var categoria: String? = null

    override fun onStart() {
        super.onStart()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val produtos = mutableListOf(
            Produto(R.drawable.ps5, "PlayStation 5", "R$ 3.999,99", "Games"),
            Produto(R.drawable.chuteita_nike, "Chuteira Nike Becco Futsal", "R$ 299,99", "Esporte"),
            Produto(R.drawable.megafone, "Megafone", "R$ 70,00", "Objeto")
        )
        lista = view.findViewById(R.id.mRecycler)
        btnAtualizar = view.findViewById(R.id.btnAtualizar)
        produtoAdapter = ProdutoAdapter { nome ->
            Toast.makeText(requireContext(), "Produto: $nome", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), InspecionarProduto::class.java)
            intent.putExtra("nome", nome)

            startActivity(
                intent
            )
        }
        produtoAdapter.atualizarListaProdutos(
            produtos
        )
        lista.adapter = produtoAdapter
        lista.layoutManager = LinearLayoutManager(requireContext())

        btnAtualizar.setOnClickListener {
            produtos.add(
                Produto(R.drawable.megafone, "Megafone2", "R$ 70,00", "Objeto")
            )
            produtoAdapter.atualizarListaProdutos( produtos )
        }
    }

}