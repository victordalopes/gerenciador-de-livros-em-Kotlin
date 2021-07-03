package com.example.livrosemkotlin.telas

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.livrosemkotlin.R

class EditoraTela : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telae)

        setupActionBarWithNavController(findNavController(R.id.fragmentE))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}