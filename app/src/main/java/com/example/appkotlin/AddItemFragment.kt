package com.example.appkotlin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_add_item, container, false)
        val buttonCriarAnuncio = view.findViewById<Button>(R.id.buttonCriarAnuncio)
        val buttonMeusAnuncios = view.findViewById<Button>(R.id.buttonMeusAnuncios)

        buttonCriarAnuncio.setOnClickListener {
            val intent = Intent(requireContext(), CriarAnuncio::class.java)
            startActivity(intent)
        }
        buttonMeusAnuncios.setOnClickListener {
            val intent = Intent(requireContext(), MeusAnuncios::class.java)
            startActivity(intent)
        }

        return view

    }



}
