package com.zerodev.zeromanga.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.listeners.CapituloOnClickListener
import com.zerodev.zeromanga.net.models.Capitulo

class AdapterCapitulos(val capitulos : MutableList<Capitulo>, val capituloOnClickListener: CapituloOnClickListener) : RecyclerView.Adapter<AdapterCapitulos.CapituloViewHolder>() {


    class CapituloViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        private val titleCapitulo : TextView = itemView.findViewById(R.id.tv_title_capitulo)

        fun bind(capitulo : Capitulo,capituloOnClickListener: CapituloOnClickListener){
            titleCapitulo.text = capitulo.Title
            itemView.setOnClickListener {
                capituloOnClickListener.onClick(capitulo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapituloViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_capitulos,parent,false)
        return CapituloViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CapituloViewHolder, position: Int) {
        val capitulo = capitulos[position]
        holder.bind(capitulo, capituloOnClickListener)
    }

    override fun getItemCount() = capitulos.size
}