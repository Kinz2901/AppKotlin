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

    private var imagem: Int? = null
    private var nome: String? = null
    private var preco: String? = null
    private var categoria: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imagem = arguments?.getInt("imagem")
        nome = arguments?.getString("nome")
        preco = arguments?.getString("preco")
        categoria = arguments?.getString("categoria")
        if (imagem != null && nome != null && preco != null && categoria != null) {
            adicionarProduto(imagem!!, nome!!, preco!!, categoria!!)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lista = view.findViewById(R.id.mRecycler)
        adapter = ProdutoAdapter(produtos)
        lista.adapter = adapter
        lista.layoutManager = LinearLayoutManager(requireContext())
    }
    fun adicionarProduto(imagem: Int, nome: String, preco: String, categoria: String) {
        println("Adicionando produto: $nome, $preco, $categoria")
        val novoProduto = Produto(imagem, nome, preco, categoria)
        produtos.add(novoProduto)
        println("Quantidade de produtos: ${produtos.size}")
        adapter.notifyItemInserted(produtos.size - 1) // Atualiza a lista no RecyclerView
    }


}