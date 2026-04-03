package com.example.myapplication
import java.io.Serializable

data class Car(
    val name: String,
    val price: Double,
    val imageResId: Int = 0 // სურათის ID, ამ ეტაპზე 0 იყოს
) : Serializable