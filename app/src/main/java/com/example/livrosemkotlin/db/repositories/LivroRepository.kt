package com.example.livrosemkotlin.db.repositories

import androidx.lifecycle.LiveData
import com.example.livrosemkotlin.beans.Livro
import com.example.livrosemkotlin.db.MainDAO

class LivroRepository (private val mainDAO: MainDAO) {

    val lerLivro: LiveData<List<Livro>> = mainDAO.lerLivro()

    suspend fun insertLivro(livro: Livro){
        mainDAO.insertLivro(livro)
    }

    suspend fun alteraLivro(livro: Livro){
        mainDAO.alteraLivro(livro)
    }

    suspend fun deleteLivro(livro: Livro){
        mainDAO.deleteLivro(livro)
    }

    suspend fun deleteLivros(){
        mainDAO.deleteLivros()
    }
}