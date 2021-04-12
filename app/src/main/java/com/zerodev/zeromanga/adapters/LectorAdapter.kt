package com.zerodev.zeromanga.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.zerodev.zeromanga.R
import com.zerodev.zeromanga.databinding.VisorItemBinding

class LectorAdapter (val imagenes : List<String>) : RecyclerView.Adapter<LectorAdapter.LectorViewHolder>() {

    private var binding : VisorItemBinding? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectorViewHolder {
        //val itemView = LayoutInflater.from(parent.context).inflate(R.layout.visor_item,parent,false)
        binding = VisorItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LectorViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: LectorViewHolder, position: Int) {
        val imagen = imagenes[position]
        holder.bind(imagen)
    }

    override fun getItemCount() = imagenes.size

     class LectorViewHolder(itemBinding: VisorItemBinding) : RecyclerView.ViewHolder(itemBinding.root){

        private val photoView : PhotoView = itemView.findViewById(R.id.pv_imagen_capitulo)

        fun bind( imagen : String){
            Glide
                .with(itemView
                    .context)
                .load(imagen)
                .into(photoView)
        }

    }
}