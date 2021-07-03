package com.example.livrosemkotlin.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.livrosemkotlin.R
import com.example.livrosemkotlin.beans.Livro
import kotlinx.android.synthetic.main.row_livro.view.*

class LivroAdapter: RecyclerView.Adapter<LivroAdapter.MyViewHolder>(){

    private var livroList = emptyList<Livro>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_livro, parent, false))
    }

    override fun getItemCount(): Int {
        return livroList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = livroList[position]
        holder.itemView.idl_txt.text = currentItem.id.toString()
        holder.itemView.titulo_txt.text = currentItem.titulo
        holder.itemView.autor_txt.text = currentItem.autor
        holder.itemView.editoral_txt.text = currentItem.editora

        holder.itemView.rowL.setOnClickListener{
            val action = LivroListFragmentDirections.actionLivroListFragmentToLivroUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(livro: List<Livro>){
        this.livroList = livro
        notifyDataSetChanged()
    }
}