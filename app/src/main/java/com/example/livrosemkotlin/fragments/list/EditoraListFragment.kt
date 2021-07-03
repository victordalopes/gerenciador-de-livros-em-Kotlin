package com.example.livrosemkotlin.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.livrosemkotlin.R
import com.example.livrosemkotlin.db.viewmodels.EditoraViewModel
import kotlinx.android.synthetic.main.fragment_editora_list.view.*

class EditoraListFragment : Fragment() {

    private lateinit var mEditoraViewModel: EditoraViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_editora_list, container, false)

        val adapter = EditoraAdapter()
        val recyclerViewE = view.recyclerViewE
        recyclerViewE.adapter = adapter
        recyclerViewE.layoutManager = LinearLayoutManager(requireContext())

        mEditoraViewModel = ViewModelProvider(this).get(EditoraViewModel::class.java)
        mEditoraViewModel.lerEditora.observe(
            viewLifecycleOwner,
            { editora -> adapter.setData(editora) })

        view.floatingActionButtonE.setOnClickListener {
            findNavController().navigate(R.id.action_editoraListFragment_to_editoraAddFragment)
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteEditoras()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteEditoras() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim") { _, _ ->
            mEditoraViewModel.deleteEditoras()
            Toast.makeText(requireContext(), "Tabela esvaziada", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("Não") { _, _ -> }
        builder.setTitle("Esvaziar tabela?")
        builder.setMessage("Tem certeza de que quer esvaziar a tabela?")
        builder.create().show()
    }
}