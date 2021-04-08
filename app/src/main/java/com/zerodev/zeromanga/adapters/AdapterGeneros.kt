package com.zerodev.zeromanga.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.databinding.RowGenerosBinding

class AdapterGeneros () : RecyclerView.Adapter<AdapterGeneros.GeneroHolder>()  {


    private  var binding : RowGenerosBinding? = null

    class GeneroHolder(itemBinding: RowGenerosBinding) : RecyclerView.ViewHolder(itemBinding.root){

    }

    private val differCallback = object : DiffUtil.ItemCallback<String>(){

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneroHolder {
        //val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_generos,parent,false)
        binding = RowGenerosBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GeneroHolder(binding!!)
    }

    override fun onBindViewHolder(holder: GeneroHolder, position: Int) {
       val genero = differ.currentList[position]
        holder.itemView.apply {
            binding?.tvGenero?.text = genero
        }
       // holder.itemView.tv_genero.text = genero
    }

    override fun getItemCount(): Int {
       return  differ.currentList.size
    }
}