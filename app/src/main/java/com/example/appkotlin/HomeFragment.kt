package com.example.appkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appkotlin.model.Produto
import com.example.appkotlin.view.ProdutoAdapter

class HomeFragment : Fragment() {
    private lateinit var adapter: ProdutoAdapter

    private val produtos = mutableListOf(
        Produto(R.drawable.ps5, "PlayStation 5", "R$ 3.999,99", "Games"),
        Produto(R.drawable.chuteita_nike, "Chuteira Nike Becco Futsal", "R$ 299,99", "Esporte")
    )
    private lateinit var lista: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val lista = view.findViewById<RecyclerView>(R.id.mRecycler)
        adapter = ProdutoAdapter(produtos)
        lista.adapter = adapter
        lista.layoutManager = LinearLayoutManager(requireContext())
        adicionarProduto(R.drawable.megafone, "Megafone", "R$ 70.00","Objeto")
    }
    fun adicionarProduto(imagem: Int, nome: String, preco: String, categoria: String) {
        val novoProduto = Produto(imagem, nome, preco, categoria)
        produtos.add(novoProduto)
        adapter.notifyItemInserted(produtos.size - 1) // Atualiza a lista no RecyclerView
    }


}