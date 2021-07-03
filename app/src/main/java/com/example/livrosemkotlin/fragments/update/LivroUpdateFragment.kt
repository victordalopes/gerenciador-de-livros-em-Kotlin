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
import com.example.livrosemkotlin.beans.Livro
import com.example.livrosemkotlin.db.viewmodels.LivroViewModel
import kotlinx.android.synthetic.main.fragment_livro_update.*
import kotlinx.android.synthetic.main.fragment_livro_update.view.*

class LivroUpdateFragment : Fragment() {

    private val args by navArgs<LivroUpdateFragmentArgs>()

    private lateinit var mLivroViewModel: LivroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_livro_update, container, false)

        mLivroViewModel = ViewModelProvider(this).get(LivroViewModel::class.java)

        view.updateTitulo.setText(args.currentLivro.titulo)
        view.updateAutor.setText(args.currentLivro.autor)
        view.updateEditora.setText(args.currentLivro.editora)

        view.updateLivro.setOnClickListener {
            updateItem()
        }

        setHasOptionsMenu(true)

        return view
    }
    private fun updateItem() {
        val titulo = updateTitulo.text.toString()
        val autor = updateAutor.text.toString()
        val editora = updateEditora.text.toString()

        if (inputCheck(titulo, autor, editora)) {
            // Criando objeto Livro
            val updatedLivro = Livro(args.currentLivro.id, titulo, autor, editora)
            // Atualizar Livro
            mLivroViewModel.alteraLivro(updatedLivro)
            Toast.makeText(requireContext(), "Livro atualizado com sucesso!", Toast.LENGTH_LONG)
                .show()
            // Voltar
            findNavController().navigate(R.id.action_livroUpdateFragment_to_livroListFragment)
        }
    }

    private fun inputCheck(titulo: String, autor: String, editora: String): Boolean{
        return !(TextUtils.isEmpty(titulo) && TextUtils.isEmpty(autor) && TextUtils.isEmpty(editora))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteLivro()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteLivro() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim") { _, _ ->
            mLivroViewModel.deleteLivro(args.currentLivro)
            Toast.makeText(requireContext(), "Livro excluído: ${args.currentLivro.titulo}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_livroUpdateFragment_to_livroListFragment)

        }
        builder.setNegativeButton("Não") { _, _ -> }
        builder.setTitle("Delete ${args.currentLivro.titulo}?")
        builder.setMessage("Tem certeza de que quer excluir ${args.currentLivro.titulo}?")
        builder.create().show()
    }
}