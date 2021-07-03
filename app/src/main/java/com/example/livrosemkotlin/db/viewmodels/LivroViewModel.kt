package com.example.livrosemkotlin.db.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.livrosemkotlin.beans.Livro
import com.example.livrosemkotlin.db.DBHelper
import com.example.livrosemkotlin.db.repositories.LivroRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LivroViewModel(application: Application): AndroidViewModel(application) {
    val lerLivro: LiveData<List<Livro>>
    private val repository: LivroRepository

    init {
        val mainDAO = DBHelper.getInstance(application).mainDAO()
        repository = LivroRepository(mainDAO)
        lerLivro = repository.lerLivro
    }

    fun insertLivro(livro: Livro){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertLivro(livro)
        }
    }

    fun alteraLivro(livro: Livro){
        viewModelScope.launch(Dispatchers.IO) {
            repository.alteraLivro(livro)
        }
    }

    fun deleteLivro(livro: Livro) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLivro(livro)
        }
    }

    fun deleteLivros() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteLivros()
        }
    }
}