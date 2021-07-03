package com.example.livrosemkotlin.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.livrosemkotlin.R
import com.example.livrosemkotlin.db.viewmodels.LivroViewModel
import kotlinx.android.synthetic.main.fragment_livro_list.view.*

class LivroListFragment : Fragment() {

    private lateinit var mLivroViewModel: LivroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_livro_list, container, false)

        //RecyclerView
        val adapter = LivroAdapter()
        val recyclerViewL = view.recyclerViewL
        recyclerViewL.adapter = adapter
        recyclerViewL.layoutManager = LinearLayoutManager(requireContext())

        //LivroViewModel
        mLivroViewModel = ViewModelProvider(this).get(LivroViewModel::class.java)
        mLivroViewModel.lerLivro.observe(viewLifecycleOwner, Observer { livro ->
            adapter.setData(livro)})

        view.floatingActionButtonL.setOnClickListener{
            findNavController().navigate(R.id.action_livroListFragment_to_livroAddFragment)
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete) {
            deleteLivros()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteLivros() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim") { _, _ ->
            mLivroViewModel.deleteLivros()
            Toast.makeText(requireContext(), "Tabela esvaziada", Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("Não") { _, _ -> }
        builder.setTitle("Esvaziar tabela?")
        builder.setMessage("Tem certeza de que quer esvaziar a tabela?")
        builder.create().show()
    }
}