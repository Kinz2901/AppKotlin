package com.example.appkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appkotlin.model.Produto
import com.example.appkotlin.view.ProdutoAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class HomeFragment : Fragment() {
    private lateinit var produtoAdapter: ProdutoAdapter

    private lateinit var lista: RecyclerView

    private lateinit var eventoSnapshot: ListenerRegistration
    private val bancoDados by lazy {
        FirebaseFirestore.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        lista = view.findViewById(R.id.mRecycler)
        lista.layoutManager = LinearLayoutManager(context)
        // Inicializa o ProdutoAdapter e define o Adapter no RecyclerView
        produtoAdapter = ProdutoAdapter { produto ->
            exibirMensagem("Clicou em: $produto ")
            val intent = Intent(requireContext(), InspecionarProduto::class.java)
            intent.putExtra("Imagem", produto.imagemUrl)
            intent.putExtra("Nome", produto.nome)
            intent.putExtra("Preco", produto.preco.toString())
            intent.putExtra("Descricao", produto.descricao)
            intent.putExtra("Categoria", produto.categoria)
            intent.putExtra("Quantidade", produto.quantidade.toString())
            startActivity(intent)
        }
        lista.adapter = produtoAdapter
        lista.layoutManager = LinearLayoutManager(context)

        val campoSearch = view.findViewById<EditText>(R.id.campoSeach)

        val btnFiltrarNome = view.findViewById<Button>(R.id.btnFiltrarNome)
        val btnFiltrarCategoria = view.findViewById<Button>(R.id.btnFiltrarCategoria)

        btnFiltrarNome.setOnClickListener {
            filtrarNome(campoSearch.text.toString())
        }

        btnFiltrarCategoria.setOnClickListener {
            filtrarCategoria(campoSearch.text.toString())
        }

        return view
    }

    private fun filtrarNome(filtro: String) {

        val prefixo = filtro
        eventoSnapshot = bancoDados
            .collection("produtos")
            .whereGreaterThanOrEqualTo("nome", prefixo)
            .whereLessThan("nome", prefixo + "\uf8ff")
            .addSnapshotListener { querySnapshot, error ->
                val listaProdutos = mutableListOf<Produto>()
                val documentos = querySnapshot?.documents
                documentos?.forEach { documentSnapshot ->

                    val produto = documentSnapshot.toObject( Produto::class.java )
                    if( produto != null ) {
                        listaProdutos.add(
                            0,
                            produto )
                    }
                }
                // Atualiza a lista no adapter e notifica o RecyclerView
                produtoAdapter.atualizarListaProdutos(listaProdutos)

            }
    }

    private fun filtrarCategoria(filtro: String) {

        val prefixo = filtro
        eventoSnapshot = bancoDados
            .collection("produtos")
            .whereGreaterThanOrEqualTo("categoria", prefixo)
            .whereLessThan("categoria", prefixo + "\uf8ff")
            .addSnapshotListener { querySnapshot, error ->
                val listaProdutos = mutableListOf<Produto>()
                val documentos = querySnapshot?.documents
                documentos?.forEach { documentSnapshot ->

                    val produto = documentSnapshot.toObject( Produto::class.java )
                    if( produto != null ) {
                        listaProdutos.add(
                            0,
                            produto )
                    }
                }
                // Atualiza a lista no adapter e notifica o RecyclerView
                produtoAdapter.atualizarListaProdutos(listaProdutos)

            }
    }


    override fun onStart() {
        super.onStart()
        adicionarListenerContatos()
    }

    private fun adicionarListenerContatos() {
        eventoSnapshot = bancoDados
            .collection("produtos")
            .addSnapshotListener { querySnapshot, error ->
                val listaProdutos = mutableListOf<Produto>()
                val documentos = querySnapshot?.documents
                documentos?.forEach { documentSnapshot ->

                    val produto = documentSnapshot.toObject( Produto::class.java )
                    if( produto != null ) {
                        listaProdutos.add(
                            0,
                            produto )
                    }
                }
                // Atualiza a lista no adapter e notifica o RecyclerView
                produtoAdapter.atualizarListaProdutos(listaProdutos)

            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        eventoSnapshot.remove()
    }

    private fun exibirMensagem(texto: String) {
        Toast.makeText(requireContext(), texto, Toast.LENGTH_LONG).show()
    }

}