package com.example.appkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.appkotlin.model.Produto

class HomeFragment : Fragment() {
    private lateinit var nameNewProductTextView: TextView
    private val produtos: MutableList<Produto> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        // Inflate the layout for this fragment
        val produto = Produto(
            imagem = "https://example.com/imagem.jpg",
            nome = "Produto Exemplo",
            descricao = "Este é um exemplo de descrição do produto.",
            preco = 49.99,
            quantidade = 10,
            categoria = "Eletrônicos"
        )
        produtos.add(produto)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameNewProductTextView = view.findViewById(R.id.nameNewProduct)
    }
    fun updateText(newNome: String) {
        println(newNome)
    }

}