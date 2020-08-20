package com.zerodev.zeromanga.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zerodev.zeromanga.R
import kotlinx.android.synthetic.main.row_generos.view.*

class AdapterGeneros (val generos : MutableList<String>) : RecyclerView.Adapter<AdapterGeneros.GeneroHolder>()  {

    class GeneroHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneroHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_generos,parent,false)
        return GeneroHolder(itemView)
    }

    override fun onBindViewHolder(holder: GeneroHolder, position: Int) {
       val genero = generos[position]
        holder.itemView.tv_genero.text = genero
    }

    override fun getItemCount(): Int {
       return  generos.size
    }
}