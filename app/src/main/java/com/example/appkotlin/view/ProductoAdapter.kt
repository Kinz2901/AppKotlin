package com.example.appkotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appkotlin.R
import com.example.appkotlin.model.Produto

class ProdutoAdapter(private val produtos: List<Produto>) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagem: TextView = itemView.findViewById(R.id.imagem)
        val nome: TextView = itemView.findViewById(R.id.nome)
        val preco: TextView = itemView.findViewById(R.id.preco)
        val categoria: TextView = itemView.findViewById(R.id.categoria)
        // Adicione outros componentes de visualização, como imagem, descrição, etc.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produto, parent, false)
        return ProdutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = produtos[position]
        holder.imagem.text = produto.imagem
        holder.nome.text = produto.nome
        val preco_formatado = "R$ ${produto.preco}"
        holder.preco.text = preco_formatado
        holder.categoria.text = produto.categoria
        // Configure outros campos do produto
    }

    override fun getItemCount() = produtos.size
}