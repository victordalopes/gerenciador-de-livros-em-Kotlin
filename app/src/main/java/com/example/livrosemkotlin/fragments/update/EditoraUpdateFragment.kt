package com.example.livrosemkotlin.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.livrosemkotlin.R
import com.example.livrosemkotlin.beans.Editora
import com.example.livrosemkotlin.db.viewmodels.EditoraViewModel
import kotlinx.android.synthetic.main.fragment_editora_update.*
import kotlinx.android.synthetic.main.fragment_editora_update.view.*
import kotlinx.android.synthetic.main.fragment_livro_update.view.*

class EditoraUpdateFragment : Fragment() {

    private val args by navArgs<EditoraUpdateFragmentArgs>()

    private lateinit var mEditoraViewModel: EditoraViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_editora_update, container, false)

        mEditoraViewModel = ViewModelProvider(this).get(EditoraViewModel::class.java)

        view.updateNome.setText(args.currentEditora.nome)
        view.updateFundacao.setText(args.currentEditora.fundacao)
        view.updateSede.setText(args.currentEditora.sede)
        view.updateCnpj.setText(args.currentEditora.cnpj)

        view.updateEditora.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateItem() {
        val nome = updateNome.text.toString()
        val fundacao = updateFundacao.text.toString()
        val sede = updateSede.text.toString()
        val cnpj = updateCnpj.text.toString()

        if (inputCheck(nome, fundacao, sede, cnpj)) {
            // Criando objeto Editora
            val updatedEditora = Editora(args.currentEditora.id, nome, fundacao, sede, cnpj)
            // Atualizar Editora
            mEditoraViewModel.alteraEditora(updatedEditora)
            Toast.makeText(requireContext(), "Editora atualizada com sucesso!", Toast.LENGTH_LONG)
                    .show()
            // Voltar
            findNavController().navigate(R.id.action_editoraUpdateFragment_to_editoraListFragment)
        }
    }

    private fun inputCheck(nome: String, fundacao: String, sede: String, cnpj: String): Boolean {
        return !(TextUtils.isEmpty(nome) && TextUtils.isEmpty(fundacao) && TextUtils.isEmpty(sede) && TextUtils.isEmpty(
                cnpj))
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteEditora()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteEditora() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim") { _, _ ->
            mEditoraViewModel.deleteEditora(args.currentEditora)
            Toast.makeText(requireContext(), "Editora excluída: ${args.currentEditora.nome}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_editoraUpdateFragment_to_editoraListFragment)

        }
        builder.setNegativeButton("Não") { _, _ -> }
        builder.setTitle("Delete ${args.currentEditora.nome}?")
        builder.setMessage("Tem certeza de que quer excluir ${args.currentEditora.nome}?")
        builder.create().show()
    }
}