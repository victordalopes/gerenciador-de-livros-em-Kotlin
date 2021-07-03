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
import com.example.livrosemkotlin.beans.Editora
import com.example.livrosemkotlin.db.viewmodels.EditoraViewModel
import kotlinx.android.synthetic.main.fragment_editora_add.*
import kotlinx.android.synthetic.main.fragment_editora_add.view.*

class EditoraAddFragment : Fragment() {

    private lateinit var mEditoraViewModel: EditoraViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_editora_add, container, false)

        mEditoraViewModel = ViewModelProvider(this).get(EditoraViewModel::class.java)

        view.addEditora.setOnClickListener {
            insertEditora()
        }
        return view
    }

    private fun insertEditora() {
        val nome = editNome.text.toString()
        val fundacao = editFundacao.text.toString()
        val sede = editSede.text.toString()
        val cnpj = editCnpj.text.toString()

        if (inputCheck(nome, fundacao, sede, cnpj)) {

            val editora = Editora(0, nome, fundacao, sede, cnpj)

            mEditoraViewModel.insertEditora(editora)
            Toast.makeText(requireContext(), "Editora adicionada com sucesso!", Toast.LENGTH_LONG)
                .show()
            findNavController().navigate(R.id.action_editoraAddFragment_to_editoraListFragment)
        }else{
            Toast.makeText(requireContext(), "Favor preencher todos os campos!", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(nome: String, fundacao: String, sede: String, cnpj: String): Boolean {
        return !(TextUtils.isEmpty(nome) && TextUtils.isEmpty(fundacao) && TextUtils.isEmpty(sede) && TextUtils.isEmpty(
            cnpj))
    }
}