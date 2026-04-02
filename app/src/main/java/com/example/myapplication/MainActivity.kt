package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // activity_main.xml–ს ვაბმევთ
        setContentView(R.layout.activity_main)

        // ProductFragment ჩასმა
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, ProductFragment())
            .commit()
    }
}