package com.zerodev.zeromanga.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.databinding.RowCapitulosBinding
import com.zerodev.zeromanga.listeners.CapituloOnClickListener
import com.zerodev.zeromanga.data.remote.models.Capitulo

class AdapterCapitulos(val capitulos : List<Capitulo>, val capituloOnClickListener: CapituloOnClickListener) : RecyclerView.Adapter<AdapterCapitulos.CapituloViewHolder>() {


    private var binding : RowCapitulosBinding? = null

    class CapituloViewHolder(itemBinding: RowCapitulosBinding) : RecyclerView.ViewHolder(itemBinding.root){


        private val titleCapitulo : TextView = itemView.findViewById(R.id.tv_title_capitulo)

        fun bind(capitulo : Capitulo, capituloOnClickListener: CapituloOnClickListener){
            titleCapitulo.text = capitulo.name
            itemView.setOnClickListener {
                capituloOnClickListener.onClick(capitulo)
            }
        }

    }


  /*
  *
    private val differCallback = object : DiffUtil.ItemCallback<Capitulo>(){
        override fun areItemsTheSame(oldItem: Capitulo, newItem: Capitulo): Boolean {
            return oldItem.Title == newItem.Title
        }

        override fun areContentsTheSame(oldItem: Capitulo, newItem: Capitulo): Boolean {
            return oldItem == oldItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)
   */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CapituloViewHolder {
        //val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_capitulos,parent,false)
        binding = RowCapitulosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CapituloViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: CapituloViewHolder, position: Int) {
        val capitulo = capitulos[position]

        //holder.bind(capitulo, capituloOnClickListener)
        /*
        holder.itemView.apply {
           binding?.tvTitleCapitulo?.text = capitulo.Title

        }.setOnClickListener {
                capituloOnClickListener.onClick(capitulo)
        }
         */
        holder.bind(capitulo = capitulo,capituloOnClickListener = capituloOnClickListener)
    }

    override fun getItemCount() = capitulos.size
}