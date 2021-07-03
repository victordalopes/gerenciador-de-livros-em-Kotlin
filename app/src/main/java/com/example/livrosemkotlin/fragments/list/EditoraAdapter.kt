package com.example.livrosemkotlin.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.livrosemkotlin.R
import com.example.livrosemkotlin.beans.Editora
import kotlinx.android.synthetic.main.row_editora.view.*

class EditoraAdapter: RecyclerView.Adapter<EditoraAdapter.MyViewHolder>(){

    private var editoraList = emptyList<Editora>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_editora, parent, false))
    }

    override fun getItemCount(): Int {
        return editoraList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = editoraList[position]
        holder.itemView.ide_txt.text = currentItem.id.toString()
        holder.itemView.nome_txt.text = currentItem.nome
        holder.itemView.fundacao_txt.text = currentItem.fundacao
        holder.itemView.sede_txt.text = currentItem.sede
        holder.itemView.cnpj_txt.text = currentItem.cnpj

        holder.itemView.rowE.setOnClickListener{
            val action = EditoraListFragmentDirections.actionEditoraListFragmentToEditoraUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(editora: List<Editora>){
        this.editoraList = editora
        notifyDataSetChanged()
    }
}