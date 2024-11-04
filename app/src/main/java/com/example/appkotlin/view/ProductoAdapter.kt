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
    private val produtos: MutableList<Produto>,
    private val clique: () -> Unit // Função lambda para o clique
) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    inner class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagem: ImageView = itemView.findViewById(R.id.imagem)
        val nome: TextView = itemView.findViewById(R.id.nome)
        val preco: TextView = itemView.findViewById(R.id.preco)
        val categoria: TextView = itemView.findViewById(R.id.categoria)
        val cardView: CardView = itemView.findViewById(R.id.cardView)

        fun bind(produto: Produto) {
            imagem.setImageResource(produto.imagem)
            nome.text = produto.nome
            preco.text = produto.preco
            categoria.text = produto.categoria

            // Chama a função clique ao clicar no cardView
            cardView.setOnClickListener {
                clique()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produto, parent, false)
        return ProdutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = produtos[position]
        holder.bind(produto)
    }

    override fun getItemCount() = produtos.size
}
