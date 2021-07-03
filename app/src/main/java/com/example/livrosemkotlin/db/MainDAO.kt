package com.example.livrosemkotlin.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.livrosemkotlin.beans.*

@Dao
interface MainDAO {

    // onConflict sinaliza ao Room o que fazer caso dois itens iguais sejam inseridos.
    // REPLACE causa a substituição dos dados.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLivro(livro: Livro)

    // A anotação "Update" é para a Query para atualizar o banco
    @Update
    suspend fun alteraLivro(livro: Livro)

    @Delete
    suspend fun deleteLivro(livro: Livro) //deleta um único item

    @Query("DELETE FROM livro")
    suspend fun  deleteLivros() //deleta todos os itens da tabela

    @Query("SELECT * FROM livro ORDER BY id ASC")
    fun lerLivro(): LiveData<List<Livro>>

    //EDITORA
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEditora(editora: Editora)

    @Update
    suspend fun alteraEditora(editora: Editora)

    @Delete
    suspend fun deleteEditora(editora: Editora)

    @Query ("DELETE FROM editora")
    suspend fun deleteEditoras()

    @Query("SELECT * FROM editora ORDER BY id ASC")
    fun lerEditora(): LiveData<List<Editora>>
}