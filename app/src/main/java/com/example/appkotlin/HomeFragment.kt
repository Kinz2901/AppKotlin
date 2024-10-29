package com.example.appkotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appkotlin.model.Produto
import com.example.appkotlin.view.ProdutoAdapter

class HomeFragment : Fragment() {
    private lateinit var nameNewProductTextView: TextView
    private lateinit var lista: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        lista = view.findViewById(R.id.mRecycler)
        val produtos = listOf(
            Produto("PlayStation 5", "3.999,99", "Games"),
            Produto("Chuteira Nike Beco Futsal", "299,99", "Esporte"),
        )
        val adapter = ProdutoAdapter(produtos)
        lista.adapter = adapter
        lista.layoutManager = LinearLayoutManager(requireContext())

    }


}