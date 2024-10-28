package com.example.appkotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appkotlin.R

class ProductAdapter  : RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    class ProductHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.nome)
        val preco: TextView = itemView.findViewById(R.id.preco)
        val categoria: TextView = itemView.findViewById(R.id.categoria)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produto, parent, false)
        return ProductHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        TODO("Not yet implemented")
    }
}