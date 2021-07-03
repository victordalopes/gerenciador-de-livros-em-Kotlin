package com.example.livrosemkotlin.telas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.livrosemkotlin.R

class LivroTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telal)

        setupActionBarWithNavController(findNavController(R.id.fragmentL))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}