package com.example.livrosemkotlin.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.livrosemkotlin.R
import com.example.livrosemkotlin.beans.Livro
import com.example.livrosemkotlin.db.viewmodels.LivroViewModel
import kotlinx.android.synthetic.main.fragment_livro_add.*
import kotlinx.android.synthetic.main.fragment_livro_add.view.*

class LivroAddFragment : Fragment() {

    private lateinit var mLivroViewModel: LivroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_livro_add, container, false)

        mLivroViewModel = ViewModelProvider(this).get(LivroViewModel::class.java)

        view.addLivro.setOnClickListener {
            insertLivro()
        }
        return view
    }

    private fun insertLivro() {
        val titulo = editTitulo.text.toString()
        val autor = editAutor.text.toString()
        val editora = editEditora.text.toString()

        if(inputCheck(titulo, autor, editora)) {
            //Criar objeto livro
            val livro = Livro(0, titulo, autor, editora)
            //Adicionando dados ao banco
            mLivroViewModel.insertLivro(livro)
            Toast.makeText(requireContext(), "Livro adicionado com sucesso!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_livroAddFragment_to_livroListFragment)
        }else{
            Toast.makeText(requireContext(), "Favor preencher todos os campos!", Toast.LENGTH_LONG).show()
        }
    }

    //Função para checar se os campos foram preeenchidos
    private fun inputCheck(titulo: String, autor: String, editora: String): Boolean{
        return !(TextUtils.isEmpty(titulo) && TextUtils.isEmpty(autor) && TextUtils.isEmpty(editora))
    }
}