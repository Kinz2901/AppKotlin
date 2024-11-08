package com.example.appkotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.appkotlin.R
import com.example.appkotlin.model.Produto

class ProdutoAdapter(
    private val clique: (String) -> Unit // Função lambda para o clique
) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    private var listaProdutos = mutableListOf<Produto>()

    fun atualizarListaProdutos( lista: MutableList<Produto>) {
        listaProdutos = lista
        notifyDataSetChanged()
    }

    inner class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagem: ImageView = itemView.findViewById(R.id.imagem)
        val nome: TextView = itemView.findViewById(R.id.nome)
        val preco: TextView = itemView.findViewById(R.id.preco)
        val categoria: TextView = itemView.findViewById(R.id.categoria)
        val cardView: CardView = itemView.findViewById(R.id.cardView)

        fun bind(produto: Produto) {
            imagem.setImageResource(produto.imagem)
            nome.text = produto.nome
            val precoConvertido = "R$ ${produto.preco}"
            preco.text =  precoConvertido
            categoria.text = produto.categoria

            // Chama a função clique ao clicar no cardView
            cardView.setOnClickListener {
                clique( produto.nome )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produto, parent, false)
        return ProdutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = listaProdutos[position]
        holder.bind(produto)
    }

    override fun getItemCount() = listaProdutos.size
}
