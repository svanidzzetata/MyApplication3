package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentProductBinding

class ProductFragment : Fragment(R.layout.fragment_product) {
    private lateinit var binding: FragmentProductBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductBinding.bind(view)

        // BMW არჩევა (btnBmw-ს ნაცვლად ვიყენებთ cardBmw-ს)
        binding.cardBmw.setOnClickListener {
            sendData(Car("BMW M3", 38000.0))
        }

        // Ferrari არჩევა
        binding.cardFerrari.setOnClickListener {
            sendData(Car("Ferrari 488", 260000.0))
        }

        // Mercedes არჩევა
        binding.cardMercedes.setOnClickListener {
            sendData(Car("Mercedes CLA", 46400.0))
        }

        // Porsche არჩევა
        binding.cardPorsche.setOnClickListener {
            sendData(Car("Porsche 911", 189000.0))
        }
    }

    private fun sendData(car: Car) {
        val bundle = Bundle()
        bundle.putSerializable("car_data", car)

        val orderFragment = OrderFragment()
        orderFragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, orderFragment)
            .addToBackStack(null)
            .commit()
    }
}