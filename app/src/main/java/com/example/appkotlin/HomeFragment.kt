package com.example.appkotlin

import android.os.Bundle
import android.util.Log
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
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nameNewProductTextView = view.findViewById(R.id.nameNewProduct)

    }


}