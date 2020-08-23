package com.zerodev.zeromanga.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.zerodev.zeromanga.R

class LectorAdapter(val imagenes : MutableList<String>) : RecyclerView.Adapter<LectorAdapter.LectorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectorViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.visor_item,parent,false)
        return LectorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LectorViewHolder, position: Int) {
        val imagen = imagenes[position]
        holder.bind(imagen)
    }

    override fun getItemCount() = imagenes.size

     class LectorViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
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