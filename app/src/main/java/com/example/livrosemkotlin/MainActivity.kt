package com.example.livrosemkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.livrosemkotlin.telas.EditoraTela
import com.example.livrosemkotlin.telas.LivroTela
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLivro.setOnClickListener {
            val intent = Intent(this, LivroTela::class.java)
            startActivity(intent)
        }

        btnEditora.setOnClickListener {
            val intent = Intent(this, EditoraTela::class.java)
            startActivity(intent)
        }
    }
}