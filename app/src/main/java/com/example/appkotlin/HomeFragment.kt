package com.example.appkotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class HomeFragment : Fragment() {
    private lateinit var nameNewProductTextView: TextView

    companion object {
        private const val ARG_PARAM1 = "nome"
        private const val ARG_PARAM2 = "price"

        fun newInstance(param1: String, param2: Double): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putDouble(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nome = arguments?.getString(ARG_PARAM1)
        val price = arguments?.getDouble(ARG_PARAM2)

        nameNewProductTextView = view.findViewById(R.id.nameNewProduct)
    }
    fun updateText(newNome: String) {
        println(newNome)
    }


}