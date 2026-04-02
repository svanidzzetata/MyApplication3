package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class ProductFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_product, container, false)

        val bmwBtn = view.findViewById<Button>(R.id.bmwBtn)
        val mercBtn = view.findViewById<Button>(R.id.mercBtn)

        bmwBtn.setOnClickListener {
            openOrder("BMW M3", 260000.0)
        }

        mercBtn.setOnClickListener {
            openOrder("Mercedes CLA", 220000.0)
        }

        return view
    }

    private fun openOrder(name: String, price: Double) {
        val fragment = OrderFragment()
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putDouble("price", price)
        fragment.arguments = bundle

        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.frameLayout, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}