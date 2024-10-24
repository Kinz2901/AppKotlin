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
}
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