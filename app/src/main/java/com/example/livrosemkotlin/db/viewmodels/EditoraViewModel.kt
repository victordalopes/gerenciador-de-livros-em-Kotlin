package com.example.livrosemkotlin.db.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.livrosemkotlin.beans.Editora
import com.example.livrosemkotlin.db.DBHelper
import com.example.livrosemkotlin.db.repositories.EditoraRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditoraViewModel(application: Application): AndroidViewModel(application) {
    val lerEditora: LiveData<List<Editora>>
    private val repository: EditoraRepository

    init {
        val mainDAO = DBHelper.getInstance(application).mainDAO()
        repository = EditoraRepository(mainDAO)
        lerEditora = repository.lerEditora
    }

    fun insertEditora(editora: Editora){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertEditora(editora)
        }
    }

    fun alteraEditora(editora: Editora){
        viewModelScope.launch(Dispatchers.IO){
            repository.alteraEditora(editora)
        }
    }

    fun deleteEditora(editora: Editora){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEditora(editora)
        }
    }

    fun deleteEditoras(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteEditoras()
        }
    }
}