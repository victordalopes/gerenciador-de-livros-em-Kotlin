package com.example.livrosemkotlin.db.repositories

import androidx.lifecycle.LiveData
import com.example.livrosemkotlin.beans.Editora
import com.example.livrosemkotlin.db.MainDAO

class EditoraRepository (private val mainDAO: MainDAO) {

    val lerEditora: LiveData<List<Editora>> = mainDAO.lerEditora()

    suspend fun insertEditora(editora: Editora){
        mainDAO.insertEditora(editora)
    }

    suspend fun alteraEditora(editora: Editora){
        mainDAO.alteraEditora(editora)
    }

    suspend fun deleteEditora(editora: Editora){
        mainDAO.deleteEditora(editora)
    }

    suspend fun deleteEditoras(){
        mainDAO.deleteEditoras()
    }
}