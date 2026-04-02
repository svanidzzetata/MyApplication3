package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class OrderFragment : Fragment() {

    private var basePrice: Double = 0.0
    private var finalPrice: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_order, container, false)

        val nameText = view.findViewById<TextView>(R.id.productName)
        val priceText = view.findViewById<TextView>(R.id.finalPrice)
        val expressCheck = view.findViewById<CheckBox>(R.id.expressCheck)
        val payBtn = view.findViewById<Button>(R.id.payBtn)

        // მიღება bundle-დან
        val bundle = arguments
        val name = bundle?.getString("name")
        basePrice = bundle?.getDouble("price") ?: 0.0

        nameText.text = name

        // 5% ფასდაკლება
        finalPrice = basePrice * 0.95
        priceText.text = "Final: $$finalPrice"

        // Express checkbox listener
        expressCheck.setOnCheckedChangeListener { _, isChecked ->
            priceText.text = if (isChecked) {
                "Final: $${finalPrice + 1700}"
            } else {
                "Final: $$finalPrice"
            }
        }

        // Pay button listener
        payBtn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.frameLayout, SuccessFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        return view
    }
}