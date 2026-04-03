package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentOrderBinding

class OrderFragment : Fragment(R.layout.fragment_order) {
    private lateinit var binding: FragmentOrderBinding
    private var basePrice: Double = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderBinding.bind(view)

        // მონაცემების მიღება და UI-ს შევსება
        val car = arguments?.getSerializable("car_data") as? Car

        car?.let {
            basePrice = it.price
            binding.tvItemName.text = it.name
            binding.tvItemPrice.text = "$${String.format("%,.0f", it.price)}"

            // სურათის შეცვლა არჩეული მანქანის მიხედვით
            val imageRes = when(it.name) {
                "BMW M3" -> R.drawable.img_bmw
                "Ferrari 488" -> R.drawable.img_ferrari
                "Mercedes CLA" -> R.drawable.img_mercedes
                "Porsche 911" -> R.drawable.img_porsche
                else -> R.drawable.img_bmw
            }
            binding.ivCarThumb.setImageResource(imageRes)
        }

        // გამოთვლის გამოძახება თავიდანვე
        updateUI()

        // როცა მიწოდებას ვცვლით (Standard/Express)
        binding.radioGroupShipping.setOnCheckedChangeListener { _, _ ->
            updateUI()
        }

        // გადახდის ღილაკი
        binding.btnPay.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SuccessFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun updateUI() {
        // 5% ფასდაკლების გამოთვლა
        val discount = basePrice * 0.05
        val priceAfterDiscount = basePrice - discount

        // მიწოდების საფასური
        val shippingExtra = if (binding.rbExpress.isChecked) 1700.0 else 0.0
        val total = priceAfterDiscount + shippingExtra

        // საბოლოო ფასის გამოტანა ფორმატირებით: Total $264,400
        binding.tvTotalAmount.text = "Total $${String.format("%,.0f", total)}"
    }
}